package ru.baumanclass.datasource.local.mapper

import ru.baumanclass.authentification.hash
import ru.baumanclass.datasource.remote.model.requests.login_request.RegisterRequest
import ru.baumanclass.datasource.remote.model.responses.login_response.UserModelResponse
import ru.baumanclass.domain.model.UserModel

fun UserModel.toResponse(): UserModelResponse = UserModelResponse(
    uuid = uuid,
    email = email,
    password = password,
    surname = surname,
    name = name,
    patronymic = patronymic,
    role = role
)

fun RegisterRequest.toDomain(): UserModel = UserModel(
    uuid = null,
    email = email,
    password = hash(password),
    surname = surname,
    name = name,
    patronymic = patronymic,
    role = role
)