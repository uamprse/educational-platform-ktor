package ru.baumanclass.datasource.remote.model.requests.lesson_request

import kotlinx.serialization.Serializable

@Serializable
data class UpdLessonDescRequest(
    val description: String
)
