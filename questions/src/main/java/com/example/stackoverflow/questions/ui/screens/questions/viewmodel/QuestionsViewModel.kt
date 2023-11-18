package com.example.stackoverflow.questions.ui.screens.questions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.entities.Question
import com.example.stackoverflow.questions.domain.usecases.RequestQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    val requestQuestions: RequestQuestionsUseCase,
) : ViewModel() {
    private val _flow = MutableStateFlow(QuestionsScreenState())
    val flow: StateFlow<QuestionsScreenState> = _flow

    init {
        viewModelScope.launch {
            val result = requestQuestions()
            _flow.update { state ->
                when (result) {
                    is Result.Success -> showData(state, result)
                    is Result.Failure -> showErrorMsg(state, result)
                }
            }
        }
    }

    private fun showErrorMsg(
        state: QuestionsScreenState,
        result: Result.Failure
    ) = state.copy(
        errorCode = result.errorCode,
        loading = false
    )

    private fun showData(
        state: QuestionsScreenState,
        result: Result.Success<List<Question>>
    ) = state.copy(
        questions = result.data,
        loading = false,
        errorCode = null
    )

    fun onEvent(event: QuestionEvent) {
        when (event) {
            is QuestionEvent.NavigateToAnswersById -> {
                _flow.update { state ->
                    state.copy(
                        navigateTo = QuestionsScreenState.Navigation.ToAnswersById(event.id)
                    )
                }
            }
        }
    }

    fun onNavigated() {
        _flow.update { state ->
            state.copy(
                navigateTo = null
            )
        }
    }

}