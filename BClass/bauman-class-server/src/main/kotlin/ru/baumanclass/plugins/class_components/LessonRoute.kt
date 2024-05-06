package ru.baumanclass.plugins.class_components

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.baumanclass.authentification.checkRoleOfUser
import ru.baumanclass.datasource.local.db.entity.primary.user.Role
import ru.baumanclass.utils.ErrorConstant
import ru.baumanclass.datasource.local.mapper.toDomain
import ru.baumanclass.datasource.local.mapper.toResponse
import ru.baumanclass.datasource.remote.model.requests.lesson_request.UpdLessonDescRequest
import ru.baumanclass.datasource.remote.model.requests.lesson_request.UpdLessonTitleRequest
import ru.baumanclass.datasource.remote.exceptions.LessonNotFoundException
import ru.baumanclass.datasource.remote.model.requests.lesson_request.LessonModelRequest
import ru.baumanclass.datasource.remote.model.requests.lesson_request.GroupOfLessonRequest
import ru.baumanclass.datasource.remote.model.responses.login_response.BaseResponse
import ru.baumanclass.domain.usecaseimpl.LessonUseCaseImpl
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl
import ru.baumanclass.utils.ErrorConstant.ERROR_OCCURRED
import ru.baumanclass.utils.ErrorConstant.GENERAL
import ru.baumanclass.utils.ErrorConstant.INVALID_COURSE_DATA
import ru.baumanclass.utils.ErrorConstant.INVALID_LESSON_DATA
import ru.baumanclass.utils.ErrorConstant.LESSON_DELETED
import ru.baumanclass.utils.ErrorConstant.LESSON_NOT_FOUND
import ru.baumanclass.utils.ErrorConstant.NOT_ENOUGH_RIGHTS
import java.util.*

fun Route.LessonRoute(userUseCaseImpl: UserUseCaseImpl) {

    val lessonUseCaseImpl: LessonUseCaseImpl by inject()

    authenticate("jwt") {
        post("api/v1/lessons") {
            val addLessonRequest = call.receiveNullable<LessonModelRequest>() ?: run {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, GENERAL))
                return@post
            }

            val token = userUseCaseImpl.getTokenFromRequest(call).toString()

            if (checkRoleOfUser(token, userUseCaseImpl)) {
                call.respond(HttpStatusCode.Forbidden, BaseResponse(false, NOT_ENOUGH_RIGHTS))
            }

            try {
                val lesson = addLessonRequest.toDomain()
                val response = lessonUseCaseImpl.createLesson(lesson).toResponse()
                call.respond(HttpStatusCode.OK, response)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, BaseResponse(false, GENERAL))
            }
        }


        get("/api/v1/courses/{courseUuid}/lessons") {

            val courseUuid = call.parameters["courseUuid"]?.let { UUID.fromString(it) }

            if (courseUuid == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, INVALID_COURSE_DATA))
                return@get
            }

            try {
                val lessons = lessonUseCaseImpl.getLessons(GroupOfLessonRequest(courseUuid)).map { lessonModel ->
                    lessonModel.toResponse()
                }

                call.respond(HttpStatusCode.OK, lessons)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, ERROR_OCCURRED))
            }
        }

        patch("/api/v1/lessons/{lessonUuid}/description") {

            val lessonUuid = call.parameters["lessonUuid"]?.let { UUID.fromString(it) }

            if (lessonUuid == null) {
                call.respond(HttpStatusCode.BadRequest, INVALID_LESSON_DATA)
                return@patch
            }


            val request = call.receive<UpdLessonDescRequest>()

            try {
                lessonUseCaseImpl.updateLessonDescription(lessonUuid, request.description)
                call.respond(HttpStatusCode.OK)
            } catch (e: LessonNotFoundException) {
                call.respond(HttpStatusCode.NotFound, LESSON_NOT_FOUND)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, GENERAL)
            }
        }

        patch("/api/v1/lessons/{lessonUuid}/title") {

            val lessonUuid = call.parameters["lessonUuid"]?.let { UUID.fromString(it) }

            if (lessonUuid == null) {
                call.respond(HttpStatusCode.BadRequest, INVALID_LESSON_DATA)
                return@patch
            }


            val request = call.receive<UpdLessonTitleRequest>()

            try {
                lessonUseCaseImpl.updateLessonTitle(lessonUuid, request.title)
                call.respond(HttpStatusCode.OK)
            } catch (e: LessonNotFoundException) {
                call.respond(HttpStatusCode.NotFound, LESSON_NOT_FOUND)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, GENERAL)
            }
        }

        get("/api/v1/lessons/{lessonUuid}") {

            val lessonUuid = call.parameters["lessonUuid"]?.let { UUID.fromString(it) }

            if (lessonUuid == null) {
                call.respond(HttpStatusCode.BadRequest, INVALID_LESSON_DATA)
                return@get
            }


            try {
                val lesson = lessonUseCaseImpl.getLesson(lessonUuid)?.toResponse()

                call.respond(HttpStatusCode.OK, lesson ?: LESSON_NOT_FOUND)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, GENERAL)
            }
        }

        delete("/api/v1/lessons/{lessonUuid}") {

            val lessonUuid = call.parameters["lessonUuid"]?.let { UUID.fromString(it) }

            if (lessonUuid == null) {
                call.respond(HttpStatusCode.BadRequest, INVALID_LESSON_DATA)
                return@delete
            }

            val token = userUseCaseImpl.getTokenFromRequest(call).toString()

            if (checkRoleOfUser(token, userUseCaseImpl)) {
                call.respond(HttpStatusCode.Forbidden, BaseResponse(false, NOT_ENOUGH_RIGHTS))
            }

            try {
                lessonUseCaseImpl.deleteLesson(lessonUuid)
                call.respond(HttpStatusCode.OK, LESSON_DELETED)
            } catch (e: LessonNotFoundException) {
                call.respond(HttpStatusCode.NotFound, LESSON_NOT_FOUND)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, GENERAL)
            }
        }
    }
}
