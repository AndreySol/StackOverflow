package com.example.stackoverflow.questions.domain.usecases

import com.example.stackoverflow.common.Result
import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Question
import javax.inject.Inject

class RequestQuestionsUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {

    suspend operator fun invoke(): Result<List<Question>> {
        return repository.requestQuestions()
    }
}