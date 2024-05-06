package ru.baumanclass.domain.usecase

import ru.baumanclass.datasource.remote.model.requests.lesson_request.GroupOfLessonRequest
import ru.baumanclass.domain.model.LessonModel
import java.util.*

interface LessonUseCase {
    suspend fun createLesson(lessonModel: LessonModel): LessonModel
    suspend fun getLessons(groupUuid: GroupOfLessonRequest): List<LessonModel>
    suspend fun getLesson(lessonUuid: UUID): LessonModel?
    suspend fun updateLessonDescription(lessonUuid: UUID, newDescription: String)
    suspend fun updateLessonTitle(lessonUuid: UUID, newTitle: String)
    suspend fun deleteLesson(lessonUuid: UUID)
}