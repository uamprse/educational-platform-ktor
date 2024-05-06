package ru.baumanclass.domain.usecaseimpl

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.DecodedJWT
import io.ktor.server.application.*
import ru.baumanclass.authentification.JwtService
import ru.baumanclass.domain.model.UserModel
import ru.baumanclass.domain.repository.UserRepository
import ru.baumanclass.domain.usecase.UserUseCase

class UserUseCaseImpl(
    private val repositoryImpl: UserRepository,
    private val jwtService: JwtService
): UserUseCase {
    override suspend fun createUser(userModel: UserModel) = repositoryImpl.addNewUser(userModel = userModel)

    override suspend fun findUserByEmail(email: String) = repositoryImpl.getUserByEmail(email = email)

    override fun generateToken(userModel: UserModel): String = jwtService.generateToken(user = userModel)

    override fun getGwtVerifier(): JWTVerifier = jwtService.getVerifier()

    override fun getRoleFromToken(token: String): String? = jwtService.getRoleFromToken(token)


    fun getTokenFromRequest(call: ApplicationCall): String? = jwtService.getTokenFromRequest(call)

}
