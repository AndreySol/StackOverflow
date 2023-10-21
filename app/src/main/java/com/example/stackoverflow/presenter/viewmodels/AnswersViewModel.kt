package com.example.stackoverflow.presenter.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.domain.questions.RequestAnswersByQuestionId
import com.example.stackoverflow.domain.questions.RequestQuestionById
import com.example.stackoverflow.presenter.screens.questions.AnswersScreenState
import com.example.stackoverflow.presenter.screens.questions.QuestionIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val requestAnswersByQuestionId: RequestAnswersByQuestionId,
    private val requestQuestionById: RequestQuestionById
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