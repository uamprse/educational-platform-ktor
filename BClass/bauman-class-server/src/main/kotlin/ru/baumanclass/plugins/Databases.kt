package ru.baumanclass.plugins

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.baumanclass.datasource.local.db.entity.auxiliary.*
import ru.baumanclass.datasource.local.db.entity.primary.user.UserEntity
import ru.baumanclass.datasource.local.db.entity.primary.question.QuestionEntity
import ru.baumanclass.datasource.local.db.entity.primary.*

object DatabasesFactory {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val dbUrl = System.getenv("DB_POSTGRES_URL")
    private val dbUser = System.getenv("DB_POSTGRES_USER")
    private val dbPassword = System.getenv("DB_POSTGRES_PASSWORD")

    fun Application.initializationDatabase() {
        Database.connect(getHikariDatasource())

        transaction {
            SchemaUtils.create(
                UserEntity,
                CourseEntity,
                LessonEntity,
                MaterialEntity,
                TestEntity,
                QuestionEntity,
                UserCourseEntity,
                UserTestEntity,
                UserQuestionEntity
            )
        }
    }

    private fun getHikariDatasource(): HikariDataSource {

        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = dbUrl
        config.username = dbUser
        config.password = dbPassword
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T {
        return withContext(Dispatchers.IO) {
            transaction { block() }
        }
    }
}
