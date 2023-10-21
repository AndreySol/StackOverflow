package com.example.stackoverflow.questions.data.dto

data class OwnerDto(
    val accept_rate: Int,
    val account_id: Int,
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String
)
