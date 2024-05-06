package ru.baumanclass.datasource.local.mapper

import ru.baumanclass.datasource.remote.model.requests.lesson_request.LessonModelRequest
import ru.baumanclass.datasource.remote.model.responses.lesson_response.LessonModelResponse
import ru.baumanclass.domain.model.LessonModel

fun LessonModel.toResponse(): LessonModelResponse = LessonModelResponse(
    uuid = uuid,
    title = title,
    description = description,
    courseUuid = courseUuid
)

fun LessonModelRequest.toDomain(): LessonModel = LessonModel(
    uuid = null,
    title = title,
    description = description,
    courseUuid = courseUuid
)
