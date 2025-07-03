package com.example.stackoverflow.answers.ui.screens.viewmodel

import com.example.stackoverflow.answers.domain.entities.QuestionWithAnswers
import com.example.stackoverflow.common.ErrorCode


data class AnswersScreenState(
    val loading: Boolean = true,
    val errorCode: ErrorCode? = null,
    val questionWithAnswers: QuestionWithAnswers? = null
)
