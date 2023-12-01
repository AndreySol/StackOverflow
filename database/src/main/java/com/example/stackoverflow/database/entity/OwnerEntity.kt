package com.example.stackoverflow.database.entity

import androidx.room.Entity

@Entity
data class OwnerEntity(
    val acceptRate: Int,
    val accountId: Int,
    val displayName: String,
    val link: String,
    val profileImage: String,
    val reputation: Int,
    val userId: Int,
    val userType: String
)
