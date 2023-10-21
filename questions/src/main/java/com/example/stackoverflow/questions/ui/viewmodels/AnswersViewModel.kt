package com.example.stackoverflow.questions.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.questions.domain.usecases.RequestAnswersByQuestionIdUseCase
import com.example.stackoverflow.questions.domain.usecases.RequestQuestionByIdUseCase
import com.example.stackoverflow.questions.navigation.QuestionIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val requestAnswersByQuestionId: RequestAnswersByQuestionIdUseCase,
    private val requestQuestionById: RequestQuestionByIdUseCase
) : ViewModel() {
    private val questionIdArgs = QuestionIdArgs(savedStateHandle)

    private val _flow = MutableStateFlow<AnswersScreenState>(AnswersScreenState.Loading)
    val flow: StateFlow<AnswersScreenState> = _flow

    init {
        viewModelScope.launch {
            val answers = requestAnswersByQuestionId(questionIdArgs.questionId)
            val question = requestQuestionById(questionIdArgs.questionId)
            _flow.update {
                AnswersScreenState.Loaded(
                    question = question,
                    answers = answers
                )
            }
        }
    }
}