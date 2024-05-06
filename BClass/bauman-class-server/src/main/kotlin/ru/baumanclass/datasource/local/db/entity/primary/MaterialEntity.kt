package ru.baumanclass.datasource.local.db.entity.primary

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import ru.baumanclass.datasource.local.db.entity.DatabaseConstant

object MaterialEntity : UUIDTable(name = "Material") {
    val filename: Column<String> = varchar("filename", length = DatabaseConstant.FILENAME_LENGTH)
    val lessonUuid = uuid("uuid_lesson").references(LessonEntity.id)
}
