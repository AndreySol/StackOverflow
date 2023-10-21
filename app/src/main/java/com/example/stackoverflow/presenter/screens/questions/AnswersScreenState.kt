package com.example.stackoverflow.presenter.screens.questions

import com.example.stackoverflow.domain.questions.entities.Answer
import com.example.stackoverflow.domain.questions.entities.Question

sealed interface AnswersScreenState {
    object Loading : AnswersScreenState
    data class Loaded(
        val question: Question,
        val answers: List<Answer>
    ) : AnswersScreenState
}
