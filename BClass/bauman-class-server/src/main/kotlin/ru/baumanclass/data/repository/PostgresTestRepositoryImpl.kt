package ru.baumanclass.data.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import ru.baumanclass.datasource.local.db.entity.primary.TestEntity
import ru.baumanclass.domain.model.TestModel
import ru.baumanclass.datasource.remote.model.requests.test_request.GetAllTestsRequest
import ru.baumanclass.domain.repository.TestRepository
import ru.baumanclass.plugins.DatabasesFactory.dbQuery
import java.util.*

class PostgresTestRepositoryImpl : TestRepository {

    override suspend fun addNewTest(testModel: TestModel): TestModel {

        val dbQueryResult = dbQuery {
            TestEntity.insert { table ->
                table[title] = testModel.title
                table[deadline] = testModel.deadline
                table[lessonUuid] = testModel.lessonUuid
            }
        }

        testModel.uuid = dbQueryResult.get(TestEntity.id).value

        return testModel
    }

    override suspend fun getTestsInLesson(lessonUuidRequest: GetAllTestsRequest): List<TestModel> {

        val lessonUuid = lessonUuidRequest.lessonUuid

        return dbQuery {
            TestEntity.select { TestEntity.lessonUuid.eq(lessonUuid) }
                .map { rowToTest(it) }
        }
    }

    private fun rowToTest(row: ResultRow): TestModel {
        return TestModel(
            uuid = row[TestEntity.id].value,
            title = row[TestEntity.title],
            deadline = row[TestEntity.deadline],
            lessonUuid = row[TestEntity.lessonUuid]
        )
    }

    override suspend fun getTestByUuid(testUuid: UUID): TestModel? {
        return dbQuery {
            TestEntity.select { TestEntity.id eq testUuid }
                .map { rowToTest(it) }
                .singleOrNull()
        }
    }

    override suspend fun deleteTestByUuid(testUuid: UUID) {
        dbQuery {
            TestEntity.deleteWhere { TestEntity.id eq testUuid }
        }
    }
}
