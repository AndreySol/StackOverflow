package com.example.stackoverflow.api

import com.example.stackoverflow.common.AppLogger
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question
import java.net.UnknownHostException
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : QuestionsRepository {
    override suspend fun requestQuestions(): Result<List<Question>> {

        return try {
            val questions = remoteDataSource.requestQuestions()
            val data = questions?.items?.map {
                Question(
                    id = it.question_id,
                    title = it.title,
                    author = it.owner.display_name,
                    authorImage = it.owner.profile_image
                )
            } ?: emptyList()
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

    override suspend fun requestAnswersByQuestionId(questionId: Int): Result<List<Answer>> {

        return try {
            val answers = remoteDataSource.requestAnswersByQuestionId(questionId)

            val data = answers?.items?.map {
                Answer(
                    id = it.answer_id,
                    author = it.owner.display_name,
                    authorImage = it.owner.profile_image,
                    body = it.body
                )
            } ?: emptyList()

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