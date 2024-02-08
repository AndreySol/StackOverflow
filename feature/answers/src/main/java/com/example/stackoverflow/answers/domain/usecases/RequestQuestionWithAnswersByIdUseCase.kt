package com.example.stackoverflow.answers.domain.usecases

import com.example.stackoverflow.answers.domain.AnswersRepository
import com.example.stackoverflow.answers.domain.entities.QuestionWithAnswers
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question
import javax.inject.Inject

class RequestQuestionWithAnswersByIdUseCase @Inject constructor(
    private val answersRepository: AnswersRepository,
    private val questionsRepository: QuestionsRepository
) {
    suspend operator fun invoke(questionId: Int): Result<QuestionWithAnswers> {
        val question: Question?
        val answersList: List<Answer>

        when (val questionResult = questionsRepository.requestQuestionById(questionId)) {
            is Result.Failure -> return Result.Failure(ErrorCode.PARAMETER_ERROR)
            is Result.Success -> question = questionResult.data
        }

        if (question == null) {
            return Result.Failure(ErrorCode.PARAMETER_ERROR)
        }

        when (val answersResult = answersRepository.requestAnswersByQuestionId(questionId)) {
            is Result.Failure -> return Result.Failure(ErrorCode.PARAMETER_ERROR)
            is Result.Success -> answersList = answersResult.data
        }

        val data = QuestionWithAnswers(
            question = question,
            answers = answersList
        )

        return Result.Success(data)
    }
}