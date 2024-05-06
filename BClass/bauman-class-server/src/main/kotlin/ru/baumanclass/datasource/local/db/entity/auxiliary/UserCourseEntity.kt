package ru.baumanclass.datasource.local.db.entity.auxiliary

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import ru.baumanclass.datasource.local.db.entity.primary.CourseEntity
import ru.baumanclass.datasource.local.db.entity.primary.user.UserEntity

object UserCourseEntity : UUIDTable(name = "UserCourse") {
    val userUuid = uuid("uuid_user").references(UserEntity.id)
    val courseUuid = uuid("uuid_course").references(CourseEntity.id)
    val personCourseMark: Column<Int?> = integer("person_course_mark").nullable()

    init {
        personCourseMark.inList(listOf(0, 1, 2, 3, 4, 5))
    }
}
