package com.example.stackoverflow.questions.domain

import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question

interface QuestionsRepository {
    suspend fun requestQuestions(): List<Question>
    suspend fun requestQuestionById(id: Int): Question
    suspend fun requestAnswersByQuestionId(questionId: Int): List<Answer>
}