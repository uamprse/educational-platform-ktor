package ru.baumanclass.authentification

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import io.ktor.server.application.*
import ru.baumanclass.domain.model.UserModel
import java.time.LocalDateTime
import java.time.ZoneOffset

class JwtService {
    private val issuer = "-"
    private val jwtSecret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    private val verifier: JWTVerifier = JWT
            .require(algorithm)
            .withIssuer(issuer)
            .build()

    fun generateToken(user: UserModel): String{
        return JWT.create()
                .withSubject("Authentification")
                .withIssuer(issuer)
                .withClaim("email", user.email)
                .withClaim("role", user.role.toString())
                .withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC))
                .sign(algorithm)
    }

    fun getVerifier(): JWTVerifier = verifier

    fun getRoleFromToken(token: String): String? {
        val decodedJWT: DecodedJWT = JWT.decode(token)
        return decodedJWT.getClaim("role").asString()
    }

    fun getTokenFromRequest(call: ApplicationCall): String? {
        return call.request.headers["Authorization"]?.removePrefix("Bearer ")
    }

}
