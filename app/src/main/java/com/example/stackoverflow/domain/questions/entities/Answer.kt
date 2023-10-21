package com.example.stackoverflow.domain.questions.entities

data class Answer(
    val id: Int,
    val author: String,
    val authorImage: String,
    val body: String
)
