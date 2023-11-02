package com.example.stackoverflow.api.dto

import com.example.stackoverflow.api.dto.QuestionDto

data class QuestionsDto(
    val has_more: Boolean,
    val items: List<QuestionDto>,
    val quota_max: Int,
    val quota_remaining: Int,
)