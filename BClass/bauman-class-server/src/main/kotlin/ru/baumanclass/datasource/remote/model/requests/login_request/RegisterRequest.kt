package ru.baumanclass.datasource.remote.model.requests.login_request

import kotlinx.serialization.Serializable
import ru.baumanclass.datasource.local.db.entity.primary.user.Role

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val surname: String,
    val name: String,
    val patronymic: String?,
    val role: Role
)
