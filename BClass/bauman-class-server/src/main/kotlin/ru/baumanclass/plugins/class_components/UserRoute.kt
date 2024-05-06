package ru.baumanclass.plugins.class_components

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.baumanclass.authentification.hash
import ru.baumanclass.datasource.local.mapper.toResponse
import ru.baumanclass.datasource.local.mapper.toDomain
import ru.baumanclass.datasource.remote.exceptions.addNewUserException
import ru.baumanclass.datasource.remote.model.requests.login_request.LoginRequest
import ru.baumanclass.datasource.remote.model.requests.login_request.RegisterRequest
import ru.baumanclass.datasource.remote.model.responses.login_response.BaseResponse
import ru.baumanclass.domain.model.UserModel
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl
import ru.baumanclass.utils.ErrorConstant.GENERAL
import ru.baumanclass.utils.ErrorConstant.INCORRECT_PASSWORD
import ru.baumanclass.utils.ErrorConstant.INVALID_REQUEST
import ru.baumanclass.utils.ErrorConstant.USER_ALREADY_EXISTS
import ru.baumanclass.utils.ErrorConstant.USER_NOT_FOUND
import ru.baumanclass.utils.ErrorConstant.WRONG_EMAIL


fun Route.UserRoute(userUseCaseImpl: UserUseCaseImpl) {

    post("api/v1/signup") {
        val registerRequest = call.receiveNullable<RegisterRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, INVALID_REQUEST))
            return@post
        }

        try {
            val user = registerRequest.toDomain()

            val response = userUseCaseImpl.createUser(user).toResponse()

            call.respond(HttpStatusCode.OK, response)
        } catch (e: addNewUserException) {
            call.respond(HttpStatusCode.Conflict, BaseResponse(false, USER_ALREADY_EXISTS))
        }

    }

    post("api/v1/login") {
        val loginRequest = call.receiveNullable<LoginRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, INVALID_REQUEST))
            return@post
        }

        try {
            val user = userUseCaseImpl.findUserByEmail(loginRequest.email.trim().lowercase())

            if (user == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, WRONG_EMAIL))
            } else if (hash(loginRequest.password) != user.password) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, INCORRECT_PASSWORD))
            } else {
                val token = userUseCaseImpl.generateToken(user)
                call.respond(HttpStatusCode.OK, BaseResponse(true, token))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, GENERAL))
        }
    }


    authenticate("jwt") {
        post("api/v1/get-user-info") {
            try {
                val user = call.principal<UserModel>()?.toResponse()

                if (user != null) {
                    call.respond(HttpStatusCode.OK, user)
                } else{
                    call.respond(HttpStatusCode.Conflict, BaseResponse(false, USER_NOT_FOUND))
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, BaseResponse(false, GENERAL))
            }
        }
    }
}
