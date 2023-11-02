package com.example.stackoverflow.api

import com.example.stackoverflow.api.dto.QuestionsDto
import com.example.stackoverflow.common.exceptions.ApiException
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

    suspend fun requestQuestionById(id: Int): com.example.stackoverflow.api.dto.QuestionsDto? {
        val response = api.requestQuestionById(id)

        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            throw Exception(error.toString())
        }
    }

    suspend fun requestAnswersByQuestionId(questionId: Int): com.example.stackoverflow.api.dto.AnswersDto? {
        val response = api.requestAnswersByQuestionId(questionId)

        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            throw Exception(error.toString())
        }
    }
}