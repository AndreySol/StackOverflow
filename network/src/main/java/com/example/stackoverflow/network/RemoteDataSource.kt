package com.example.stackoverflow.network

import com.example.stackoverflow.network.dto.QuestionsDto
import com.example.stackoverflow.common.exceptions.ApiException
import com.example.stackoverflow.network.dto.AnswersDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val questionApi: QuestionsApi,
    private val answersApi: AnswersApi
) {
    suspend fun requestQuestions(): QuestionsDto? {
        val response = questionApi.requestQuestions()

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
        val response = questionApi.requestQuestionById(id)

        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            throw Exception(error.toString())
        }
    }

    suspend fun requestAnswersByQuestionId(questionId: Int): AnswersDto? {
        val response = answersApi.requestAnswersByQuestionId(questionId)

        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            throw Exception(error.toString())
        }
    }
}