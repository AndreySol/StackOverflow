package com.example.stackoverflow.presenter.screens.questions

import com.example.stackoverflow.domain.questions.entities.Question

sealed interface QuestionsScreenState {
    object Loading : QuestionsScreenState
    data class Loaded(
        val questions: List<Question>
    ) : QuestionsScreenState
}