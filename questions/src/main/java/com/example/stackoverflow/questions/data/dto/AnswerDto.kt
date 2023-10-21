package com.example.stackoverflow.questions.data.dto

data class AnswerDto(
    val answer_id: Int,
    val body: String,
    val content_license: String,
    val creation_date: Int,
    val is_accepted: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val owner: OwnerDto,
    val question_id: Int,
    val score: Int
)
