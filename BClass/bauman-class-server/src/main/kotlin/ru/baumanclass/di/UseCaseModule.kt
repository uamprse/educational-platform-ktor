package ru.baumanclass.di

import org.koin.dsl.module
import ru.baumanclass.authentification.JwtService
import ru.baumanclass.data.repository.PostgresLessonRepositoryImpl
import ru.baumanclass.data.repository.PostgresTestRepositoryImpl
import ru.baumanclass.data.repository.PostgresUserRepositoryImpl
import ru.baumanclass.domain.repository.LessonRepository
import ru.baumanclass.domain.repository.TestRepository
import ru.baumanclass.domain.repository.UserRepository
import ru.baumanclass.domain.usecaseimpl.LessonUseCaseImpl
import ru.baumanclass.domain.usecaseimpl.TestUseCaseImpl
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl

val useCaseModule = module {
    single{ JwtService() }
    single<UserRepository> { PostgresUserRepositoryImpl() }
    single<LessonRepository> { PostgresLessonRepositoryImpl() }
    single<TestRepository> { PostgresTestRepositoryImpl() }
    single { UserUseCaseImpl(get(), get()) }
    single { LessonUseCaseImpl(get()) }
    single { TestUseCaseImpl(get()) }
}
