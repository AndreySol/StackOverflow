package com.example.stackoverflow.questions.domain.usecases

import com.example.stackoverflow.common.AppLogger
import com.example.stackoverflow.common.Constants
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.QuestionWithAnswers
import java.net.UnknownHostException
import java.util.logging.Logger
import javax.inject.Inject

class RequestQuestionWithAnswersByIdUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) {
    suspend operator fun invoke(questionId: Int): Result<QuestionWithAnswers> {

        try {
            val question = questionsRepository.requestQuestionById(questionId)
            if (question == null) {
                Logger.getLogger(Constants.TAG).warning("Error: Question is null")
                return Result.Failure(errorCode = ErrorCode.PARAMETER_ERROR)
            }
            val answers = questionsRepository.requestAnswersByQuestionId(questionId)

            return Result.Success(
                QuestionWithAnswers(
                    question = question,
                    answers = answers
                )
            )
        } catch (e: UnknownHostException) {
            AppLogger.error(e)
            return Result.Failure(errorCode = ErrorCode.UNKNOWN_HOST)
        } catch (e: Exception) {
            AppLogger.error(e)
            return Result.Failure(errorCode = ErrorCode.UNKNOWN_ERROR)
        }


    }
}