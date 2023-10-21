package com.example.stackoverflow.data.questionsapi.dto

data class AnswersDto(
    val has_more: Boolean,
    val items: List<AnswerDto>,
    val quota_max: Int,
    val quota_remaining: Int
)


