package com.example.stackoverflow.questions.ui.viewmodels

import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question

sealed interface AnswersScreenState {
    object Loading : AnswersScreenState
    data class Loaded(
        val question: Question,
        val answers: List<Answer>
    ) : AnswersScreenState
}
