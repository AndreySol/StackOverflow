package com.example.stackoverflow.questions.domain.entities

data class QuestionWithAnswers(
    val question: Question,
    val answers: List<Answer>
)