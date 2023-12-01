package com.example.stackoverflow.database.entity

import androidx.room.Entity

@Entity
data class AnswerEntity(
    val answerId: Int,
    val body: String,
    val contentLicense: String,
    val creationDate: Int,
    val isAccepted: Boolean,
    val lastActivityDate: Int,
    val lastEditDate: Int,
    val owner: OwnerEntity,
    val questionId: Int,
    val score: Int
)
