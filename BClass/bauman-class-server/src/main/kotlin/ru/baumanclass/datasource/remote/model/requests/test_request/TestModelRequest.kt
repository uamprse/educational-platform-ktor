package ru.baumanclass.datasource.remote.model.requests.test_request

import kotlinx.serialization.Serializable
import ru.baumanclass.utils.InstantSerializer
import ru.baumanclass.utils.UuidSerializer
import java.time.Instant
import java.util.*

@Serializable
data class TestModelRequest(

    val title: String,

    @Serializable(with = InstantSerializer::class)
    val deadline: Instant,

    @Serializable(with = UuidSerializer::class)
    val lessonUuid: UUID
)
