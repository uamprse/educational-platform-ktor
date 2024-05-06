package ru.baumanclass.domain.repository

import ru.baumanclass.domain.model.UserModel

interface UserRepository {
    suspend fun getUserByEmail(email: String): UserModel?
    suspend fun addNewUser(userModel: UserModel): UserModel
}
