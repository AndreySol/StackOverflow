package com.example.stackoverflow.domain.questions

import com.example.stackoverflow.domain.QuestionsRepository
import com.example.stackoverflow.domain.questions.entities.Answer
import javax.inject.Inject

class RequestAnswersByQuestionId @Inject constructor(
    private val repository: QuestionsRepository
) {

    suspend operator fun invoke(questionId: Int): List<Answer> {
        return repository.requestAnswersByQuestionId(questionId)
    }
}