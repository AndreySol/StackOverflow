package com.example.stackoverflow.database.entity

import androidx.room.Entity

@Entity
data class AnswersEntity(
    val hasMore: Boolean,
    val items: List<AnswerEntity>,
    val quotaMax: Int,
    val quotaRemaining: Int
)


