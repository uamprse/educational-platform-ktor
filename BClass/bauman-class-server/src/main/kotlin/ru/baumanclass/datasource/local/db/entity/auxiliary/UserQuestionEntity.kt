package ru.baumanclass.datasource.local.db.entity.auxiliary

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import ru.baumanclass.datasource.local.db.entity.primary.question.QuestionEntity
import ru.baumanclass.datasource.local.db.entity.primary.user.UserEntity

object UserQuestionEntity : UUIDTable(name = "UserQuestion") {
    val userUuid = uuid("uuid_user").references(UserEntity.id)
    val questionUuid = uuid("uuid_question").references(QuestionEntity.id)
    val studentAnswer: Column<String?> = text("student_answer").nullable()
    val chatCompletion: Column<String?> = text("chat_completion").nullable()
    val studentQuestionMark: Column<Int?> = integer("student_question_mark").nullable()

    init {
        studentQuestionMark.inList(listOf(0, 1, 2, 3, 4, 5))
    }
}
