package com.example.stackoverflow.domain.questions

import com.example.stackoverflow.domain.QuestionsRepository
import com.example.stackoverflow.domain.questions.entities.Question
import javax.inject.Inject

class RequestQuestionsUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {
    suspend operator fun invoke(): List<Question> {
        return repository.requestQuestions()
    }
}