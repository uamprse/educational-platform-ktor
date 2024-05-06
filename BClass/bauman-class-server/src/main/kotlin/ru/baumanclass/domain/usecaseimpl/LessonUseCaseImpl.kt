package ru.baumanclass.domain.usecaseimpl

import ru.baumanclass.domain.model.LessonModel
import ru.baumanclass.datasource.remote.model.requests.lesson_request.GroupOfLessonRequest
import ru.baumanclass.domain.repository.LessonRepository
import ru.baumanclass.domain.usecase.LessonUseCase
import java.util.*

class LessonUseCaseImpl  (
    private val repositoryImpl: LessonRepository
) : LessonUseCase {

    override suspend fun getLessons(groupUuid: GroupOfLessonRequest): List<LessonModel> =
        repositoryImpl.getLessonsInCourse(groupUuid)

    override suspend fun getLesson(lessonUuid: UUID): LessonModel? = repositoryImpl.getLessonByUuid(lessonUuid)

    override suspend fun updateLessonDescription(lessonUuid: UUID, newDescription: String) {
        repositoryImpl.updateLessonDescription(lessonUuid, newDescription)
    }

    override suspend fun updateLessonTitle(lessonUuid: UUID, newTitle: String) {
        repositoryImpl.updateLessonTitle(lessonUuid, newTitle)
    }

    override suspend fun deleteLesson(lessonUuid: UUID) {
        repositoryImpl.deleteLessonByUuid(lessonUuid)
    }

    override suspend fun createLesson(lessonModel: LessonModel) = repositoryImpl.addNewLesson(lessonModel)

}
