package ru.baumanclass.domain.model

import java.util.UUID

data class LessonModel(
    var uuid: UUID?,
    val title: String,
    val description: String,
    val courseUuid: UUID
)
