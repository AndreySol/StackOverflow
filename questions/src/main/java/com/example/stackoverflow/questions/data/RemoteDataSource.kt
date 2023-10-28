package com.example.stackoverflow.questions.data

import com.example.stackoverflow.common.exceptions.ApiException
import com.example.stackoverflow.questions.data.api.QuestionsApi
import com.example.stackoverflow.questions.data.dto.AnswersDto
import com.example.stackoverflow.questions.data.dto.QuestionsDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: QuestionsApi
) {
    suspend fun requestQuestions(): QuestionsDto? {
        val response = api.requestQuestions()

        if (response.isSuccessful) {
            return response.body()
        } else {
            val errorCode = response.code()
            val errorMsg = response.errorBody().toString()
            throw ApiException(
                errorCode,
                errorMsg
            )
        }
    }

    suspend fun requestQuestionById(id: Int): QuestionsDto? {
        val response = api.requestQuestionById(id)

        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            throw Exception(error.toString())
        }
    }

    suspend fun requestAnswersByQuestionId(questionId: Int): AnswersDto? {
        val response = api.requestAnswersByQuestionId(questionId)

        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            throw Exception(error.toString())
        }
    }
}