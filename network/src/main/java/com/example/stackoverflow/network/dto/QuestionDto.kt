package com.example.stackoverflow.network.dto

data class QuestionDto(
    val accepted_answer_id: Int,
    val answer_count: Int,
    val closed_date: Int,
    val closed_reason: String,
    val content_license: String,
    val creation_date: Int,
    val is_answered: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: OwnerDto,
    val question_id: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val view_count: Int,
    val body: String?
)