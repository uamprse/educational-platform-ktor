package ru.baumanclass.datasource.local.db.entity.primary.user

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import ru.baumanclass.datasource.local.db.entity.DatabaseConstant

object UserEntity : UUIDTable(name = "User") {
    val email: Column<String> = varchar("email", DatabaseConstant.EMAIL_LENGTH).uniqueIndex()
    val passwordHash: Column<String> =
        varchar("password_hash", length = DatabaseConstant.PASSWORD_HASH_LENGTH).uniqueIndex()
    val surname: Column<String> = varchar("surname", length = DatabaseConstant.SURNAME_LENGTH)
    val name: Column<String> = varchar("name", length = DatabaseConstant.NAME_LENGTH)
    val patronymic: Column<String?> = varchar("patronymic", length = DatabaseConstant.PATRONYMIC_LENGTH).nullable()
    val role = enumerationByName("role", length = DatabaseConstant.ROLE_LENGTH, Role::class)
}
