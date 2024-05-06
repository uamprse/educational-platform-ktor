package ru.baumanclass.datasource.local.db.entity.primary

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import ru.baumanclass.datasource.local.db.entity.DatabaseConstant

object LessonEntity : UUIDTable(name = "Lesson") {
    val title: Column<String> = varchar("title", length = DatabaseConstant.TITLE_LENGTH)
    val description: Column<String?> = varchar("description", length = DatabaseConstant.DESCRIPTION_LENGTH).nullable()
    val courseUuid = uuid("uuid_group").references(CourseEntity.id)
}
