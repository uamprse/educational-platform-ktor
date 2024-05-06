package ru.baumanclass.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl
import ru.baumanclass.plugins.class_components.LessonRoute
import ru.baumanclass.plugins.class_components.TestRoute
import ru.baumanclass.plugins.class_components.UserRoute

fun Application.configureRouting(
    userUseCaseImpl: UserUseCaseImpl
) {
    routing {
        UserRoute(userUseCaseImpl = userUseCaseImpl)
        LessonRoute(userUseCaseImpl = userUseCaseImpl)
        TestRoute(userUseCaseImpl= userUseCaseImpl)
    }
}
