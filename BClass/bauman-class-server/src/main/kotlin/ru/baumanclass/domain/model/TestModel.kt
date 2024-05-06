package ru.baumanclass.domain.model

import java.time.Instant
import java.util.UUID

data class TestModel(
    var uuid: UUID?,
    val title: String,
    val deadline: Instant,
    val lessonUuid: UUID
)
