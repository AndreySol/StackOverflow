package com.example.stackoverflow.network

import com.example.stackoverflow.network.dto.AnswersDto
import com.example.stackoverflow.network.dto.QuestionsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionsApi {

    @GET("/2.3/questions?order=asc&sort=activity&site=stackoverflow")
    suspend fun requestQuestions(): Response<QuestionsDto>

    @GET("/2.3/questions/{id}?&site=stackoverflow&filter=withbody")
    suspend fun requestQuestionById(@Path("id") id: Int): Response<QuestionsDto>

    @GET("/2.3/questions/{questionId}/answers?&site=stackoverflow&filter=withbody")
    suspend fun requestAnswersByQuestionId(@Path("questionId") questionId: Int): Response<AnswersDto>
}





