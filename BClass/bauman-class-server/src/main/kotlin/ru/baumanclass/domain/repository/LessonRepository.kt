package ru.baumanclass.domain.repository

import ru.baumanclass.domain.model.LessonModel
import ru.baumanclass.datasource.remote.model.requests.lesson_request.GroupOfLessonRequest
import java.util.*

interface LessonRepository {
    suspend fun addNewLesson(lessonModel: LessonModel): LessonModel
    suspend fun getLessonsInCourse(courseUuidRequest: GroupOfLessonRequest): List<LessonModel>
    suspend fun updateLessonDescription(lessonUuid: UUID, newDescription: String)
    suspend fun updateLessonTitle(lessonUuid: UUID, newTitle: String)
    suspend fun getLessonByUuid(lessonUuid: UUID): LessonModel?
    suspend fun deleteLessonByUuid(lessonUuid: UUID)
}
