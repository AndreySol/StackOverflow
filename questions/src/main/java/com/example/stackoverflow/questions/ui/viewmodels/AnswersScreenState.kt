package com.example.stackoverflow.questions.ui.viewmodels

import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.questions.domain.entities.QuestionWithAnswers

sealed interface AnswersScreenState {
    object Loading : AnswersScreenState
    data class Loaded(
        val questionWithAnswers: QuestionWithAnswers
    ) : AnswersScreenState

    data class Error(val errorCode: ErrorCode) : AnswersScreenState
}
