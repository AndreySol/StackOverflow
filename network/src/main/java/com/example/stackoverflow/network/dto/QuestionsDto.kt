package com.example.stackoverflow.network.dto

data class QuestionsDto(
    val has_more: Boolean,
    val items: List<QuestionDto>,
    val quota_max: Int,
    val quota_remaining: Int,
)