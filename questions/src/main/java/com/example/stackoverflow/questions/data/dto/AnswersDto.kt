package com.example.stackoverflow.questions.data.dto

import com.example.stackoverflow.questions.data.dto.AnswerDto

data class AnswersDto(
    val has_more: Boolean,
    val items: List<AnswerDto>,
    val quota_max: Int,
    val quota_remaining: Int
)


