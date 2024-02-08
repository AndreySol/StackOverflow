package com.example.stackoverflow.network

import com.example.stackoverflow.network.dto.AnswersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnswersApi {
    @GET("/2.3/questions/{questionId}/answers?&site=stackoverflow&filter=withbody")
    suspend fun requestAnswersByQuestionId(@Path("questionId") questionId: Int): Response<AnswersDto>
}