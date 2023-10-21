package com.example.stackoverflow.data.questions

import com.example.stackoverflow.data.questionsapi.QuestionsApi
import com.example.stackoverflow.data.questionsapi.dto.AnswersDto
import com.example.stackoverflow.data.questionsapi.dto.QuestionsDto
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