package com.example.stackoverflow.database.entity

import androidx.room.Entity

@Entity
data class QuestionEntity(
    val acceptedAnswerId: Int,
    val answerCount: Int,
    val closedDate: Int,
    val closedReason: String,
    val contentLicense: String,
    val creationDate: Int,
    val isAnswered: Boolean,
    val lastActivityDate: Int,
    val lastEditDate: Int,
    val link: String,
    val owner: OwnerEntity,
    val questionId: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val viewCount: Int,
    val body: String?
)
