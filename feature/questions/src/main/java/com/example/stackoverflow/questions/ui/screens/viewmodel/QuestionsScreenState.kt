package com.example.stackoverflow.questions.ui.screens.questions.viewmodel

import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.questions.domain.entities.Question

data class QuestionsScreenState(
    val loading: Boolean = true,
    val errorCode: ErrorCode? = null,
    val questions: List<Question> = emptyList(),
    val navigateTo: Navigation? = null
) {
    sealed interface Navigation {
        data class ToAnswersById(val questionId: Int) : Navigation
    }
}



