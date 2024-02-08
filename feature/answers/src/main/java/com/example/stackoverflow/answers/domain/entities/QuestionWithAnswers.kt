package com.example.stackoverflow.answers.domain.entities

import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question

data class QuestionWithAnswers(
    val question: Question,
    val answers: List<Answer>
)