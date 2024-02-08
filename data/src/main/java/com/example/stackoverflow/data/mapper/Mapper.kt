package com.example.stackoverflow.data.mapper

import com.example.stackoverflow.database.entity.QuestionEntity
import com.example.stackoverflow.network.dto.QuestionDto
import com.example.stackoverflow.questions.domain.entities.Question

fun QuestionDto.toQuestion() =
    Question(
        id = question_id,
        title = title,
        author = owner.display_name,
        authorImage = owner.profile_image
    )

fun QuestionDto.toEntity() =
    QuestionEntity(
        id = question_id,
        title = title,
        author = owner.display_name,
        authorImage = owner.profile_image
    )

fun QuestionEntity.toQuestion() =
    Question(
        id = id,
        title = title,
        author = author,
        authorImage = authorImage,
        cached = true
    )

