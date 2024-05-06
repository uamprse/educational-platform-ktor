package ru.baumanclass

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.ktor.ext.inject
import ru.baumanclass.di.apiModule
import ru.baumanclass.di.serviceModule
import ru.baumanclass.di.useCaseModule
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl
import ru.baumanclass.plugins.DatabasesFactory.initializationDatabase
import ru.baumanclass.plugins.class_components.initializationChat
import ru.baumanclass.plugins.configureMonitoring
import ru.baumanclass.plugins.configureRouting
import ru.baumanclass.plugins.configureSecurity

fun main() {
    startKoin {
        modules(apiModule, serviceModule, useCaseModule)
    }
    embeddedServer(Netty, port = 5438, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val userUseCaseImpl: UserUseCaseImpl by inject()

    initializationDatabase()
    initializationChat()
    configureMonitoring()
    configureSecurity(userUseCaseImpl)
    configureRouting(userUseCaseImpl)
}
