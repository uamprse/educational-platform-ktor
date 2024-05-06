package ru.baumanclass.domain.repository

import ru.baumanclass.domain.model.TestModel
import ru.baumanclass.datasource.remote.model.requests.test_request.GetAllTestsRequest
import java.util.UUID

interface TestRepository {
    suspend fun addNewTest(testModel: TestModel): TestModel
    suspend fun getTestsInLesson(lessonUuidRequest: GetAllTestsRequest): List<TestModel>
    suspend fun getTestByUuid(testUuid: UUID): TestModel?
    suspend fun deleteTestByUuid(testUuid: UUID)
}
