package com.example.stackoverflow.questions.domain

import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question

interface QuestionsRepository {
    suspend fun requestQuestions(): Result<List<Question>>
    suspend fun requestQuestionById(id: Int): Result<Question?>
    suspend fun requestAnswersByQuestionId(questionId: Int): Result<List<Answer>>
}