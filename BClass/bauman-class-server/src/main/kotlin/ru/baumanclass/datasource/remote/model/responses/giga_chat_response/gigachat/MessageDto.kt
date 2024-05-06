package ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat

import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val role: String,
    val content: String?,
)
