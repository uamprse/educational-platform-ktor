package ru.baumanclass.data.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import ru.baumanclass.datasource.local.db.entity.primary.TestEntity
import ru.baumanclass.domain.model.UserModel
import ru.baumanclass.datasource.local.db.entity.primary.user.UserEntity
import ru.baumanclass.datasource.remote.exceptions.addNewUserException
import ru.baumanclass.domain.repository.UserRepository
import ru.baumanclass.plugins.DatabasesFactory.dbQuery
import ru.baumanclass.utils.ErrorConstant.INVALID_USER_DATA
import java.sql.SQLException

class PostgresUserRepositoryImpl : UserRepository {

    override suspend fun getUserByEmail(email: String): UserModel? {
        return dbQuery {
            UserEntity.select { UserEntity.email.eq(email) }
                .map { rowToUser(row = it) }
                .singleOrNull()
        }
    }

    override suspend fun addNewUser(user: UserModel): UserModel {
        try{
            val dbQueryResult = dbQuery {
                UserEntity.insert { table ->
                    table[email] = user.email
                    table[passwordHash] = user.password
                    table[surname] = user.surname
                    table[name] = user.name
                    table[patronymic] = user.patronymic
                    table[role] = user.role
                }
            }
            user.uuid = dbQueryResult.get(UserEntity.id).value
            return user
        } catch (e: SQLException) {
            throw addNewUserException(INVALID_USER_DATA)
        }
    }

    private fun rowToUser(row: ResultRow?): UserModel? {
        if (row == null) {
            return null
        }

        return UserModel(
            uuid = row[UserEntity.id].value,
            email = row[UserEntity.email],
            password = row[UserEntity.passwordHash],
            surname = row[UserEntity.surname],
            name = row[UserEntity.name],
            patronymic = row[UserEntity.patronymic],
            role = row[UserEntity.role]
        )
    }
}
