package com.example.stackoverflow.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey
    val id: Int,
    val author: String,
    val authorImage: String,
    val title: String,
    val body: String? = null
)
