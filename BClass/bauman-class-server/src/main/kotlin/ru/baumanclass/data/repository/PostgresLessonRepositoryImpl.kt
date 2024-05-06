package ru.baumanclass.data.repository

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.baumanclass.datasource.local.db.entity.primary.LessonEntity
import ru.baumanclass.domain.model.LessonModel
import ru.baumanclass.datasource.remote.model.requests.lesson_request.GroupOfLessonRequest
import ru.baumanclass.domain.repository.LessonRepository
import ru.baumanclass.plugins.DatabasesFactory.dbQuery
import java.util.*

class PostgresLessonRepositoryImpl : LessonRepository {

    override suspend fun addNewLesson(lessonModel: LessonModel): LessonModel {

        val dbQueryResult = dbQuery {
            LessonEntity.insert { table ->
                table[title] = lessonModel.title
                table[description] = lessonModel.description
                table[courseUuid] = lessonModel.courseUuid
            }
        }

        lessonModel.uuid = dbQueryResult.get(LessonEntity.id).value

        return lessonModel
    }

    override suspend fun getLessonsInCourse(courseUuidRequest: GroupOfLessonRequest): List<LessonModel> {

        val courseUuid = courseUuidRequest.courseUuid

        return dbQuery {
            LessonEntity.select { LessonEntity.courseUuid.eq(courseUuid) }
                .map { rowToLesson(it) }
        }
    }


    private fun rowToLesson(row: ResultRow): LessonModel {
        return LessonModel(
            uuid = row[LessonEntity.id].value,
            title = row[LessonEntity.title],
            description = row[LessonEntity.description].toString(),
            courseUuid = row[LessonEntity.courseUuid]
        )
    }

    override suspend fun getLessonByUuid(lessonUuid: UUID): LessonModel? {
        return dbQuery {
            LessonEntity.select { LessonEntity.id eq lessonUuid }
                .map { rowToLesson(it) }
                .singleOrNull()
        }
    }

    override suspend fun updateLessonDescription(lessonUuid: UUID, newDescription: String) {
        dbQuery {
            LessonEntity.update({ LessonEntity.id eq lessonUuid }) {
                it[description] = newDescription
            }
        }
    }

    override suspend fun updateLessonTitle(lessonUuid: UUID, newTitle: String) {
        dbQuery {
            LessonEntity.update({ LessonEntity.id eq lessonUuid }) {
                it[title] = newTitle
            }
        }
    }

    override suspend fun deleteLessonByUuid(lessonUuid: UUID) {
        dbQuery {
            LessonEntity.deleteWhere { LessonEntity.id eq lessonUuid }
        }
    }
}
