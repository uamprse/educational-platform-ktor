package ru.baumanclass.di

import org.koin.dsl.module
import ru.baumanclass.data.datasource.remote.service.IChatAiService
import ru.baumanclass.datasource.remote.service.GigaChatService

val serviceModule = module {
    single<IChatAiService> { GigaChatService(get(), get()) }
}
