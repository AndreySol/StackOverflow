package com.example.stackoverflow.questions.domain.usecases

import com.example.stackoverflow.common.AppLogger
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Answer
import java.net.UnknownHostException
import javax.inject.Inject

class RequestAnswersByQuestionIdUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {
    suspend operator fun invoke(questionId: Int): Result<List<Answer>> {

        return try {
            val answers = repository.requestAnswersByQuestionId(questionId)
            Result.Success(answers)
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            Result.Failure(ErrorCode.UNKNOWN_HOST)
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            Result.Failure(ErrorCode.UNKNOWN_ERROR)
        }
    }
}