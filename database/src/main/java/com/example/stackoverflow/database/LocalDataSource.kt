package com.example.stackoverflow.database

import com.example.stackoverflow.database.dao.QuestionDao
import com.example.stackoverflow.database.entity.QuestionEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val questionDao: QuestionDao
) {
    suspend fun saveQuestions(questions: List<QuestionEntity>?) {
        if (!questions.isNullOrEmpty()) {
            questionDao.upsertQuestions(questions)
        }
    }

    suspend fun getQuestions(): List<QuestionEntity> {
        return questionDao.getQuestions()
    }
}