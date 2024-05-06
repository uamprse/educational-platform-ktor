package ru.baumanclass.domain.model

import io.ktor.server.auth.*
import ru.baumanclass.datasource.local.db.entity.primary.user.Role
import java.util.*

data class UserModel(
    var uuid: UUID?,
    val email: String,
    val password: String,
    val surname: String,
    val name: String,
    val patronymic: String?,
    val role: Role
) : Principal
