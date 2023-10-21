package com.example.stackoverflow.domain.questions.entities

data class Question(
    val id: Int,
    val author: String,
    val authorImage: String,
    val title: String,
    val body: String? = null
)
