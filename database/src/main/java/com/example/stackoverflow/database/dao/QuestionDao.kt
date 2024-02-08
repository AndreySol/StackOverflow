package com.example.stackoverflow.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.stackoverflow.database.entity.QuestionEntity

@Dao
interface QuestionDao {

    @Upsert
    suspend fun upsertQuestions(question: List<QuestionEntity>)

    @Query("SELECT * FROM questionentity")
    suspend fun getQuestions(): List<QuestionEntity>

    @Query("SELECT * FROM questionentity WHERE id == :id")
    suspend fun getQuestionById(id: Int): QuestionEntity
}