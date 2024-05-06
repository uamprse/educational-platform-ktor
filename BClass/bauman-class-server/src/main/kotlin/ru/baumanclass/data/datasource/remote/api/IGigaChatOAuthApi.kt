package ru.baumanclass.data.datasource.remote.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import ru.baumanclass.datasource.remote.model.responses.giga_chat_response.oauth.TokenResponse

interface IGigaChatOAuthApi {
    @FormUrlEncoded
    @POST("api/v2/oauth")
    fun getToken(
        @Header("Content-Type") contentType: String,
        @Header("Accept") accept: String,
        @Header("RqUID") rqUID: String,
        @Header("Authorization") authorization: String,
        @Field("scope") scope: String
    ): Call<TokenResponse>
}
