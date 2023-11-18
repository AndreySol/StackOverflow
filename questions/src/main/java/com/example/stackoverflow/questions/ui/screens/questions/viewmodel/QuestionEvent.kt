package com.example.stackoverflow.questions.ui.screens.questions.viewmodel

sealed interface QuestionEvent {
    data class NavigateToAnswersById(val id: Int) : QuestionEvent
}
