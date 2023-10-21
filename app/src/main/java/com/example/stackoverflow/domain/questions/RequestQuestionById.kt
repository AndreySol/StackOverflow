package com.example.stackoverflow.domain.questions

import com.example.stackoverflow.domain.QuestionsRepository
import com.example.stackoverflow.domain.questions.entities.Question
import javax.inject.Inject

class RequestQuestionById @Inject constructor(
    private val repository: QuestionsRepository
) {

    suspend operator fun invoke(id: Int): Question {
        return repository.requestQuestionById(id)
    }
}
