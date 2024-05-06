package ru.baumanclass.datasource.local.mapper

import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.ChoiceDto
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.GigaChatResponseDto
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.MessageDto
import ru.baumanclass.domain.model.gigachat.Choice
import ru.baumanclass.domain.model.gigachat.GigaChatResponse
import ru.baumanclass.domain.model.gigachat.Message

fun MessageDto.toDomain(): Message = Message(
    role = role,
    content = content ?: "no_content"
)

fun ChoiceDto.toDomain(): Choice = Choice(
    message = message.toDomain(),
    finishReason = finish_reason
)

fun GigaChatResponseDto.toDomain(): GigaChatResponse = GigaChatResponse(
    choices = choices.map { it.toDomain() }
)

fun Message.toDto(): MessageDto = MessageDto(
    role = role,
    content = content ?: null
)

fun Choice.toDto(): ChoiceDto = ChoiceDto(
    message = message.toDto(),
    finish_reason = finishReason
)

fun GigaChatResponse.toDto(): GigaChatResponseDto = GigaChatResponseDto(
    choices = choices.map { it.toDto() }
)
