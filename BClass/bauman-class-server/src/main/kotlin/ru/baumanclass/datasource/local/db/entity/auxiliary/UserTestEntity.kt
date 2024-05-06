package ru.baumanclass.datasource.local.db.entity.auxiliary

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import ru.baumanclass.datasource.local.db.entity.primary.TestEntity
import ru.baumanclass.datasource.local.db.entity.primary.user.UserEntity

object UserTestEntity : UUIDTable(name = "UserTest") {
    val userUuid = uuid("uuid_user").references(UserEntity.id)
    val testUuid = uuid("uuid_test").references(TestEntity.id)
    val studentTestMark: Column<Int?> = integer("student_test_mark").nullable()

    init {
        studentTestMark.inList(listOf(0, 1, 2, 3, 4, 5))
    }
}
