package ru.baumanclass.datasource.remote.model.responses.login_response

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import ru.baumanclass.datasource.local.db.entity.primary.user.Role
import ru.baumanclass.utils.UuidSerializer
import java.util.*

@Serializable
data class UserModelResponse(
    @Serializable(with = UuidSerializer::class)
    var uuid: UUID?,
    val email: String,
    val password: String,
    val surname: String,
    val name: String,
    val patronymic: String?,
    val role: Role
): Principal
