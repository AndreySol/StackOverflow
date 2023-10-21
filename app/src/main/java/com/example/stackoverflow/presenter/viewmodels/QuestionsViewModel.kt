package com.example.stackoverflow.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.domain.questions.RequestQuestionsUseCase
import com.example.stackoverflow.presenter.screens.questions.QuestionsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    val requestQuestionsUseCase: RequestQuestionsUseCase
) : ViewModel() {
    private val _flow = MutableStateFlow<QuestionsScreenState>(QuestionsScreenState.Loading)
    val flow: StateFlow<QuestionsScreenState> = _flow

    init {
        viewModelScope.launch {
            val questions = requestQuestionsUseCase()
            _flow.update {
                QuestionsScreenState.Loaded(questions = questions)
            }
        }
    }
}