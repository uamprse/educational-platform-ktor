package ru.baumanclass.data.datasource.remote.api

import retrofit2.Call
import retrofit2.http.*
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.GigaChatRequestDto
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.GigaChatResponseDto

interface IGigaChatChatApi {

    @POST("api/v1/chat/completions")
    fun getChatCompletions(
        @Header("Content-Type") contentType: String,
        @Header("Accept") accept: String,
        @Header("Authorization") authorization: String,
        @Body requestBody: GigaChatRequestDto
    ): Call<GigaChatResponseDto>
}
