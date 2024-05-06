package ru.baumanclass.datasource.local.db.entity.primary

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.baumanclass.datasource.local.db.entity.DatabaseConstant
import java.time.Instant

object TestEntity : UUIDTable(name = "Test") {
    val title: Column<String> = varchar("title", length = DatabaseConstant.TITLE_LENGTH)
    val deadline: Column<Instant> = timestamp("deadline")
    val lessonUuid = uuid("uuid_lesson").references(LessonEntity.id)
}
