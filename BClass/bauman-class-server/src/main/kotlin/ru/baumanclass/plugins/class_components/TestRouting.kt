package ru.baumanclass.plugins.class_components

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.baumanclass.authentification.checkRoleOfUser
import ru.baumanclass.datasource.local.mapper.toDomain
import ru.baumanclass.datasource.local.mapper.toResponse
import ru.baumanclass.datasource.remote.exceptions.LessonNotFoundException
import ru.baumanclass.datasource.remote.model.requests.test_request.TestModelRequest
import ru.baumanclass.datasource.remote.model.requests.test_request.GetAllTestsRequest
import ru.baumanclass.datasource.remote.model.responses.login_response.BaseResponse
import ru.baumanclass.domain.usecaseimpl.TestUseCaseImpl
import ru.baumanclass.domain.usecaseimpl.UserUseCaseImpl
import ru.baumanclass.utils.ErrorConstant.ERROR_OCCURRED
import ru.baumanclass.utils.ErrorConstant.GENERAL
import ru.baumanclass.utils.ErrorConstant.INVALID_TEST_DATA
import ru.baumanclass.utils.ErrorConstant.NOT_ENOUGH_RIGHTS
import ru.baumanclass.utils.ErrorConstant.TEST_DELETED
import ru.baumanclass.utils.ErrorConstant.TEST_NOT_FOUND
import java.util.*

fun Route.TestRoute(userUseCaseImpl: UserUseCaseImpl) {

    val testUseCaseImpl: TestUseCaseImpl by inject()

    authenticate("jwt") {
        post("api/v1/tests") {

            val addTestRequest = call.receiveNullable<TestModelRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, GENERAL))
                return@post
            }

            try {
                val test = addTestRequest.toDomain()

                val response = testUseCaseImpl.createTest(test).toResponse()

                val token = userUseCaseImpl.getTokenFromRequest(call).toString()

                if (checkRoleOfUser(token, userUseCaseImpl)) {
                    call.respond(HttpStatusCode.Forbidden, BaseResponse(false, NOT_ENOUGH_RIGHTS))
                }
                call.respond(HttpStatusCode.OK, response)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, BaseResponse(false, GENERAL))
            }
        }

        get("/api/v1/lessons/{lessonUuid}/tests") {

            val lessonUuid = call.parameters["lessonUuid"]?.let { UUID.fromString(it) }

            if (lessonUuid == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, INVALID_TEST_DATA))
                return@get
            }

            try {
                val tests = testUseCaseImpl.getTests(GetAllTestsRequest(lessonUuid)).map { testModel ->
                    testModel.toResponse()
                }

                call.respond(HttpStatusCode.OK, tests)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, ERROR_OCCURRED))
            }
        }

        get("/api/v1/tests/{testUuid}") {

            val testUuid = call.parameters["testUuid"]?.let { UUID.fromString(it) }

            if (testUuid == null) {
                call.respond(HttpStatusCode.BadRequest, INVALID_TEST_DATA)
                return@get
            }

            try {
                val test = testUseCaseImpl.getTest(testUuid)?.toResponse()

                call.respond(HttpStatusCode.OK, test ?: TEST_NOT_FOUND)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, GENERAL)
            }
        }

        delete("/api/v1/tests/{testUuid}") {

            val testUuid = call.parameters["testUuid"]?.let { UUID.fromString(it) }

            if (testUuid == null) {
                call.respond(HttpStatusCode.BadRequest, INVALID_TEST_DATA)
                return@delete
            }

            val token = userUseCaseImpl.getTokenFromRequest(call).toString()

            if (checkRoleOfUser(token, userUseCaseImpl)) {
                call.respond(HttpStatusCode.Forbidden, BaseResponse(false, NOT_ENOUGH_RIGHTS))
            }

            try {
                testUseCaseImpl.deleteTest(testUuid)
                call.respond(HttpStatusCode.OK, TEST_DELETED)
            } catch (e: LessonNotFoundException) {
                call.respond(HttpStatusCode.NotFound, TEST_NOT_FOUND)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, GENERAL)
            }
        }
    }
}
