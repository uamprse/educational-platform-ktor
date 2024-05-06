package ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat

data class GigaChatRequestDto(
    val model: String,
    val messages: List<MessageDto>,
)
