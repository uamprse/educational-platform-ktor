package ru.baumanclass.datasource.local.db.entity

object DatabaseConstant {

    // constants for UserEntity
    const val EMAIL_LENGTH = 100
    const val PASSWORD_HASH_LENGTH = 255
    const val SURNAME_LENGTH = 100
    const val NAME_LENGTH = 100
    const val PATRONYMIC_LENGTH = 100
    const val ROLE_LENGTH = 20

    // constants for GroupEntity
    const val INVITE_CODE_LENGTH = 36

    // constants for MaterialEntity
    const val FILENAME_LENGTH = 255

    // constants for QuestionEntity
    const val QUESTION_TYPE_LENGTH = 20

    // constants for CourseEntity, GroupEntity, LessonEntity, TestEntity, QuestionEntity
    const val TITLE_LENGTH = 100
    const val DESCRIPTION_LENGTH = 255
}
