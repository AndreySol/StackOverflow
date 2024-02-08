package com.example.stackoverflow.questions.domain

import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.entities.Question

interface QuestionsRepository {
    suspend fun requestQuestions(): Result<List<Question>>
    suspend fun requestCachedQuestions(): Result<List<Question>>
    suspend fun requestQuestionById(id: Int): Result<Question?>
}