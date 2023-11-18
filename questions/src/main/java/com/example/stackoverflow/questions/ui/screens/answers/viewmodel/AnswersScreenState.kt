package com.example.stackoverflow.questions.ui.screens.answers.viewmodel

import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.questions.domain.entities.QuestionWithAnswers


data class AnswersScreenState(
    val loading: Boolean = true,
    val errorCode: ErrorCode? = null,
    val questionWithAnswers: QuestionWithAnswers? = null
)
