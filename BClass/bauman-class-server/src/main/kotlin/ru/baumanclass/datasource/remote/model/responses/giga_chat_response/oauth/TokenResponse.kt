package ru.baumanclass.datasource.remote.model.responses.giga_chat_response.oauth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_at") val expiresAt: Long
)
