package ru.baumanclass.datasource.local.mapper

import ru.baumanclass.datasource.remote.model.requests.test_request.TestModelRequest
import ru.baumanclass.datasource.remote.model.responses.test_response.TestModelResponse
import ru.baumanclass.domain.model.TestModel

fun TestModel.toResponse(): TestModelResponse = TestModelResponse(
    uuid = uuid,
    title = title,
    deadline = deadline,
    lessonUuid = lessonUuid
)

fun TestModelRequest.toDomain(): TestModel = TestModel(
    uuid = null,
    title = title,
    deadline = deadline,
    lessonUuid = lessonUuid
)
