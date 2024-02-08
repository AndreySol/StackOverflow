package com.example.stackoverflow.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServer {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val questionsApi: QuestionsApi by lazy {
        retrofit.create(QuestionsApi::class.java)
    }

    val answersApi: AnswersApi by lazy {
        retrofit.create(AnswersApi::class.java)
    }
}