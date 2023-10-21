package com.example.stackoverflow.questions.domain.usecases

import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Question
import javax.inject.Inject

class RequestQuestionByIdUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {

    suspend operator fun invoke(id: Int): Question {
        return repository.requestQuestionById(id)
    }
}
