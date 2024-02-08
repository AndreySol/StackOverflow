package com.example.stackoverflow.questions.domain.entities

data class Question(
    val id: Int,
    val author: String,
    val authorImage: String,
    val title: String,
    val body: String? = null,
    val cached: Boolean = false
)
