package com.example.stackoverflow.questions.domain.usecases

import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Answer
import javax.inject.Inject

class RequestAnswersByQuestionIdUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {

    suspend operator fun invoke(questionId: Int): List<Answer> {
        return repository.requestAnswersByQuestionId(questionId)
    }
}