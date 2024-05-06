package ru.baumanclass.data.datasource.remote.service

import ru.baumanclass.domain.model.gigachat.GigaChatResponse

interface IChatAiService {
    fun getChatToken(): String
    fun getChatCompletion(teacherMessage: String, studentMessage: String): RequestState<GigaChatResponse>
}
