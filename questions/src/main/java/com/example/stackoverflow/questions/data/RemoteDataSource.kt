package com.example.stackoverflow.questions.data

import com.example.stackoverflow.questions.data.api.QuestionsApi
import com.example.stackoverflow.questions.data.dto.AnswersDto
import com.example.stackoverflow.questions.data.dto.QuestionsDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: QuestionsApi
) {
    suspend fun requestQuestions(): QuestionsDto? {
        return api.requestQuestions().body()
    }

    suspend fun requestQuestionById(id: Int): QuestionsDto? {
        return api.requestQuestionById(id).body()
    }

    suspend fun requestAnswersByQuestionId(questionId: Int): AnswersDto? {
        return api.requestAnswersByQuestionId(questionId).body()
    }
}