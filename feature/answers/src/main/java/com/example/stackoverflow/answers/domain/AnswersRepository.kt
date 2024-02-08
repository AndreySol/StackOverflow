package com.example.stackoverflow.answers.domain

import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.entities.Answer

interface AnswersRepository {
    suspend fun requestAnswersByQuestionId(questionId: Int): Result<List<Answer>>
}