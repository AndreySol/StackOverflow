package com.example.stackoverflow.data

import com.example.stackoverflow.answers.domain.AnswersRepository
import com.example.stackoverflow.common.AppLogger
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.database.LocalDataSource
import com.example.stackoverflow.network.RemoteDataSource
import com.example.stackoverflow.questions.domain.entities.Answer
import java.net.UnknownHostException
import javax.inject.Inject

class AnswersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AnswersRepository {

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