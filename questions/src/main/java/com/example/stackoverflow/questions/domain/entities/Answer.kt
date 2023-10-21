package com.example.stackoverflow.questions.domain.entities

data class Answer(
    val id: Int,
    val author: String,
    val authorImage: String,
    val body: String
)
