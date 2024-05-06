package ru.baumanclass.datasource.remote.model.requests.lesson_request

import kotlinx.serialization.Serializable
import ru.baumanclass.utils.UuidSerializer
import java.util.*

@Serializable
data class GroupOfLessonRequest(
    @Serializable(with = UuidSerializer::class)
    val courseUuid: UUID
)
