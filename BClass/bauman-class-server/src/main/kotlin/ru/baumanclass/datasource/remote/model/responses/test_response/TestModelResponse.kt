package ru.baumanclass.datasource.remote.model.responses.test_response

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import ru.baumanclass.utils.InstantSerializer
import ru.baumanclass.utils.UuidSerializer
import java.time.Instant
import java.util.*

@Serializable
data class TestModelResponse(

    @Serializable(with = UuidSerializer::class)
    var uuid: UUID?,

    val title: String,

    @Serializable(with = InstantSerializer::class)
    val deadline: Instant,

    @Serializable(with = UuidSerializer::class)
    val lessonUuid: UUID

) : Principal
