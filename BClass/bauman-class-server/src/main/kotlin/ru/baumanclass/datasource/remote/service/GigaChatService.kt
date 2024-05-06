package ru.baumanclass.datasource.remote.service

import org.apache.commons.codec.binary.Base64
import ru.baumanclass.data.datasource.remote.api.IGigaChatChatApi
import ru.baumanclass.data.datasource.remote.api.IGigaChatOAuthApi
import ru.baumanclass.data.datasource.remote.service.IChatAiService
import ru.baumanclass.data.datasource.remote.service.LoadingState
import ru.baumanclass.data.datasource.remote.service.NetworkError
import ru.baumanclass.data.datasource.remote.service.RequestState
import ru.baumanclass.datasource.local.mapper.toDomain
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.GigaChatRequestDto
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.MessageDto
import ru.baumanclass.domain.model.gigachat.GigaChatResponse
import ru.baumanclass.utils.*
import java.util.*

class GigaChatService(
    private val oauthApi: IGigaChatOAuthApi,
    private val chatApi: IGigaChatChatApi
) : IChatAiService {

    override fun getChatToken(): String {

        val authorization = "Basic " + Base64.encodeBase64String("$GIGA_CLIENT_ID:$GIGA_CLIENT_SECRET".toByteArray())

        val rqUID = UUID.randomUUID().toString()

        val call = oauthApi.getToken(
            contentType = GIGA_CONTENT_TYPE,
            accept = GIGA_ACCEPT,
            rqUID = rqUID,
            authorization = authorization,
            scope = GIGA_SCOPE
        )

        val response = call.execute()

        return response.body()?.accessToken ?: "no_token"
    }

    override fun getChatCompletion(
        teacherMessage: String,
        studentMessage: String
    ): RequestState<GigaChatResponse> {

        val messageContent = CHAT_TASK + TEACHER_BEGINNING + teacherMessage + STUDENT_BEGINNING + studentMessage

        val requestBody = GigaChatRequestDto(
            model = GIGA_CHAT_MODEL,
            messages = listOf(
                MessageDto(
                    "user",
                    messageContent
                )
            )
        )

        val accessToken = getChatToken()

        val call = chatApi.getChatCompletions(
            contentType = GIGA_CONTENT_TYPE,
            accept = GIGA_ACCEPT,
            authorization = "Bearer $accessToken",
            requestBody = requestBody
        )

        val response = call.execute()
        val body = response.body()?.toDomain() ?: throw NetworkError.DataNotFoundError

        val loadingState: LoadingState =
            if (response.isSuccessful)
                LoadingState.Success(SUCCESS_ANSWER)
            else
                LoadingState.Error(NetworkError.ServerError)

        return RequestState(body, loadingState)
    }

    companion object {
        const val CHAT_TASK =
            "Ответ - число.Проверь по смыслу схожесть ответов Учителя и Студента в процентах(пример ответа: 90%)"
        const val TEACHER_BEGINNING = "Ответ Учителя: "
        const val STUDENT_BEGINNING = "Ответ Студента: "

        const val SUCCESS_ANSWER = "OK"
    }
}
