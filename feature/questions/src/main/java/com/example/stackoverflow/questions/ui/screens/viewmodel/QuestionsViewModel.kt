package com.example.stackoverflow.questions.ui.screens.questions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.entities.Question
import com.example.stackoverflow.questions.domain.usecases.RequestCachedQuestionsUseCase
import com.example.stackoverflow.questions.domain.usecases.RequestQuestionsUseCase
import com.example.stackoverflow.questions.ui.screens.viewmodel.QuestionEvent
import com.example.stackoverflow.questions.ui.screens.viewmodel.QuestionsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    val requestQuestions: RequestQuestionsUseCase,
    val requestCachedQuestionsUseCase: RequestCachedQuestionsUseCase
) : ViewModel() {
    private val _flow = MutableStateFlow(QuestionsScreenState())
    val flow: StateFlow<QuestionsScreenState> = _flow

    init {
        viewModelScope.launch {

            // todo: add cache support
            /*when (val result = requestCachedQuestionsUseCase()) {
                is Result.Success -> emitData(result.data)
                is Result.Failure -> emitErrorMsg(result.errorCode)
            }*/

            when (val result = requestQuestions()) {
                is Result.Success -> emitData(result.data)
                is Result.Failure -> emitErrorMsg(result.errorCode)
            }
        }
    }

    private fun emitData(
        data: List<Question>
    ) = _flow.update { state ->
        state.copy(
            questions = data,
            loading = false,
            errorCode = null
        )
    }

    private fun emitErrorMsg(
        errorCode: ErrorCode
    ) = _flow.update { state ->
        state.copy(
            errorCode = errorCode,
            loading = false
        )
    }

    fun onEvent(event: QuestionEvent) = when (event) {
        is QuestionEvent.NavigateToAnswersById -> {
            _flow.update { state ->
                state.copy(
                    navigateTo = QuestionsScreenState.Navigation.ToAnswersById(event.id)
                )
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