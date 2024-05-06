package ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat

import kotlinx.serialization.Serializable

@Serializable
data class ChoiceDto(
    val message: MessageDto,
    val finish_reason: String
)
