package com.example.stackoverflow.database.entity

import androidx.room.Entity

@Entity
data class QuestionsEntity(
    val hasMore: Boolean,
    val items: List<QuestionEntity>,
    val quotaMax: Int,
    val quotaRemaining: Int,
)