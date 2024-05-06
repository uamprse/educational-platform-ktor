package ru.baumanclass.domain.usecase

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.DecodedJWT
import ru.baumanclass.domain.model.UserModel

interface UserUseCase {
    suspend fun createUser(userModel: UserModel): UserModel
    suspend fun findUserByEmail(email: String): UserModel?
    fun generateToken(userModel: UserModel): String
    fun getGwtVerifier(): JWTVerifier
    fun getRoleFromToken(token: String): String?
}