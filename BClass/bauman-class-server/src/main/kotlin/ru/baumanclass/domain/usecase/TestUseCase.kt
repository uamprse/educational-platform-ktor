package ru.baumanclass.domain.usecase

import ru.baumanclass.datasource.remote.model.requests.test_request.GetAllTestsRequest
import ru.baumanclass.domain.model.TestModel
import java.util.*

interface TestUseCase {
    suspend fun createTest(testModel: TestModel): TestModel
    suspend fun getTests(lessonUuid: GetAllTestsRequest) : List<TestModel>
    suspend fun getTest(testUuid: UUID): TestModel?
    suspend fun deleteTest(testUuid: UUID)
}