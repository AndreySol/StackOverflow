package com.example.stackoverflow.questions.domain.usecases

import com.example.stackoverflow.common.AppLogger
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Question
import java.net.UnknownHostException
import javax.inject.Inject

class RequestQuestionsUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {

    suspend operator fun invoke(): Result<List<Question>> {

        return try {
            val questions = repository.requestQuestions()
            Result.Success(questions)
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_HOST)
        } catch (e: Exception) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_ERROR)
        }
    }
}