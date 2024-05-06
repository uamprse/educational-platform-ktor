package ru.baumanclass.domain.usecaseimpl

import ru.baumanclass.domain.model.TestModel
import ru.baumanclass.datasource.remote.model.requests.test_request.GetAllTestsRequest
import ru.baumanclass.domain.repository.TestRepository
import ru.baumanclass.domain.usecase.TestUseCase
import java.util.UUID

class TestUseCaseImpl(
    private val testRepositoryImpl: TestRepository
) : TestUseCase {

    override suspend fun createTest(testModel: TestModel) = testRepositoryImpl.addNewTest(testModel)

    override suspend fun getTests(lessonUuid: GetAllTestsRequest): List<TestModel> =
        testRepositoryImpl.getTestsInLesson(lessonUuid)

    override suspend fun getTest(testUuid: UUID): TestModel? = testRepositoryImpl.getTestByUuid(testUuid)

    override suspend fun deleteTest(testUuid: UUID) {
        testRepositoryImpl.deleteTestByUuid(testUuid)
    }
}
