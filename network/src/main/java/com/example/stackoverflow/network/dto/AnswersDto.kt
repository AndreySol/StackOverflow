package com.example.stackoverflow.network.dto

import com.example.stackoverflow.network.dto.AnswerDto

data class AnswersDto(
    val has_more: Boolean,
    val items: List<AnswerDto>,
    val quota_max: Int,
    val quota_remaining: Int
)


