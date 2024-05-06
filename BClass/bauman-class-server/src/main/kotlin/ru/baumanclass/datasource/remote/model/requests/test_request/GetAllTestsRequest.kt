package ru.baumanclass.datasource.remote.model.requests.test_request

import kotlinx.serialization.Serializable
import ru.baumanclass.utils.UuidSerializer
import java.util.UUID

@Serializable
data class GetAllTestsRequest(
    @Serializable(with = UuidSerializer::class)
    val lessonUuid: UUID,
)
