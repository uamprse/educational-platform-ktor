package ru.baumanclass.datasource.local.db.entity.primary.question

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import ru.baumanclass.datasource.local.db.entity.DatabaseConstant
import ru.baumanclass.datasource.local.db.entity.primary.TestEntity

object QuestionEntity : UUIDTable(name = "Question") {
    val title: Column<String> = varchar("title", length = DatabaseConstant.TITLE_LENGTH)
    val type = enumerationByName("type", length = DatabaseConstant.QUESTION_TYPE_LENGTH, QuestionType::class)
    val teacherAnswer: Column<String> = text("teacher_answer")
    val testUuid = uuid("uuid_test").references(TestEntity.id)
}
