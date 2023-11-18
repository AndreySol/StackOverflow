package com.example.stackoverflow.questions.ui.screens.answers.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.usecases.RequestQuestionWithAnswersByIdUseCase
import com.example.stackoverflow.questions.navigation.QuestionIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val questionWithAnswersByIdUseCase: RequestQuestionWithAnswersByIdUseCase
) : ViewModel() {
    private val questionIdArgs = QuestionIdArgs(savedStateHandle)

    private val _flow = MutableStateFlow(AnswersScreenState())
    val flow: StateFlow<AnswersScreenState> = _flow

    init {
        viewModelScope.launch {
            val result = questionWithAnswersByIdUseCase(questionIdArgs.questionId)
            _flow.update { state ->
                when (result) {
                    is Result.Success -> {
                        state.copy(
                            loading = false,
                            questionWithAnswers = result.data,
                            errorCode = null
                        )
                    }

                    is Result.Failure -> {
                        state.copy(
                            loading = false,
                            errorCode = result.errorCode
                        )
                    }
                }

            }
        }
    }
}