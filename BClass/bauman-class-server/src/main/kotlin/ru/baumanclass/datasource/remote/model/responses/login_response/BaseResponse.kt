package ru.baumanclass.datasource.remote.model.responses.login_response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
        val success: Boolean,
        val message: String
)
