package com.example.stackoverflow.questions.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.usecases.RequestQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    val requestQuestions: RequestQuestionsUseCase
) : ViewModel() {
    private val _flow = MutableStateFlow<QuestionsScreenState>(
        QuestionsScreenState.Loading
    )
    val flow: StateFlow<QuestionsScreenState> = _flow

    init {
        viewModelScope.launch {
            val result = requestQuestions()
            _flow.update {
                when (result) {
                    is Result.Success -> {
                        QuestionsScreenState.Loaded(questions = result.data)
                    }

                    is Result.Failure -> {
                        QuestionsScreenState.Error(
                            errorCode = result.errorCode
                        )
                    }
                }

            }
        }
    }
}