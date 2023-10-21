package com.example.stackoverflow.domain

import com.example.stackoverflow.domain.questions.entities.Answer
import com.example.stackoverflow.domain.questions.entities.Question

interface QuestionsRepository {
    suspend fun requestQuestions(): List<Question>
    suspend fun requestQuestionById(id: Int): Question
    suspend fun requestAnswersByQuestionId(questionId: Int): List<Answer>
}