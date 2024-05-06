package ru.baumanclass.plugins.class_components

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.baumanclass.data.datasource.remote.service.IChatAiService
import ru.baumanclass.datasource.local.mapper.toDto
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.gigachat.AiCheckerRequestDto

fun Application.initializationChat() {
    install(ContentNegotiation) {
        json()
    }

    val gigaChatService: IChatAiService by inject()

    routing {
        post("api/v1/chat/completions") {

            val request = call.receive<AiCheckerRequestDto>()

            val result = gigaChatService.getChatCompletion(request.teacherMessage, request.studentMessage)

            call.respond(HttpStatusCode.OK, result.data.toDto())
        }
    }
}
