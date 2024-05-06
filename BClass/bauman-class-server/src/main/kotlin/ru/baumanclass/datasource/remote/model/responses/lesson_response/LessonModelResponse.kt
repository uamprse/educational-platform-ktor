package ru.baumanclass.datasource.remote.model.responses.lesson_response

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import ru.baumanclass.utils.UuidSerializer
import java.util.*

@Serializable
data class LessonModelResponse(

    @Serializable(with = UuidSerializer::class)
    var uuid: UUID?,

    val title: String,

    val description: String,

    @Serializable(with = UuidSerializer::class)
    val courseUuid: UUID

) : Principal
