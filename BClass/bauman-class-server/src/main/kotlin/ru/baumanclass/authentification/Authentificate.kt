package ru.baumanclass.authentification

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.*
import ru.baumanclass.datasource.remote.model.responses.login_response.BaseResponse
import ru.baumanclass.domain.usecaseimpl.LessonUseCaseImpl
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl
import ru.baumanclass.utils.ErrorConstant
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val hashKey = System.getenv("HASH_SECRET_KEY").toByteArray()
private val hmackey = SecretKeySpec(hashKey, "HmacSHA1")

fun hash(password: String): String{
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmackey)

    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}

fun checkRoleOfUser(token: String, userUseCaseImpl: UserUseCaseImpl): Boolean {
    val role = userUseCaseImpl.getRoleFromToken(token)
    if (role != "TEACHER") {
        return true
    }else
        return false
}
