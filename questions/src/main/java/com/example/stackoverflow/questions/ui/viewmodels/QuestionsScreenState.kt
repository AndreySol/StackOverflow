package com.example.stackoverflow.questions.ui.viewmodels

import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.questions.domain.entities.Question


sealed interface QuestionsScreenState {
    object Loading : QuestionsScreenState
    data class Loaded(
        val questions: List<Question>
    ) : QuestionsScreenState

    data class Error(val errorCode: ErrorCode) : QuestionsScreenState
}