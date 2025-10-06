package com.example.stackoverflow.data

import com.example.stackoverflow.common.AppLogger
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.data.mapper.toEntity
import com.example.stackoverflow.data.mapper.toQuestion
import com.example.stackoverflow.database.LocalDataSource
import com.example.stackoverflow.network.RemoteDataSource
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Question
import java.net.UnknownHostException
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : QuestionsRepository {
    override suspend fun requestQuestions(): Result<List<Question>> {

        return try {
            val questions = remoteDataSource.requestQuestions()

            val data = questions?.items?.map { dto ->
                dto.toQuestion()
            } ?: emptyList()

            val entities = questions?.items?.map { dto ->
                dto.toEntity()
            }

            localDataSource.saveQuestions(entities)

            Result.Success(data)
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_HOST)
        } catch (e: Exception) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_ERROR)
        }
    }

    override suspend fun requestCachedQuestions(): Result<List<Question>> {

        return try {
            val questions = localDataSource.getQuestions()

            val data = questions.map { entity ->
                entity.toQuestion()
            }

            Result.Success(data)
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_HOST)
        } catch (e: Exception) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_ERROR)
        }
    }

    override suspend fun requestQuestionById(id: Int): Result<Question?> {
        return try {
            val questions = remoteDataSource.requestQuestionById(id)
            val question = questions?.items?.first()

            val data = question?.let {
                Question(
                    id = it.question_id,
                    title = it.title,
                    author = it.owner.display_name,
                    authorImage = it.owner.profile_image,
                    body = it.body
                )
            }
            Result.Success(data)
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_HOST)
        } catch (e: Exception) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_ERROR)
        }
    }
}