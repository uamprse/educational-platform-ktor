package ru.baumanclass.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl

fun Application.configureSecurity(userUseCaseImpl: UserUseCaseImpl) {
    authentication {
        jwt("jwt") {
            verifier(userUseCaseImpl.getGwtVerifier())
            realm = "Service server"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = userUseCaseImpl.findUserByEmail(email = email)
                user
            }
        }
    }
}
