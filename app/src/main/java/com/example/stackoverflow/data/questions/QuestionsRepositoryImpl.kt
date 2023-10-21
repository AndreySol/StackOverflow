package com.example.stackoverflow.data.questions

import com.example.stackoverflow.domain.QuestionsRepository
import com.example.stackoverflow.domain.questions.entities.Answer
import com.example.stackoverflow.domain.questions.entities.Question
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : QuestionsRepository {
    override suspend fun requestQuestions(): List<Question> {
        val questions = remoteDataSource.requestQuestions()

        return questions?.items?.map {
            Question(
                id = it.question_id,
                title = it.title,
                author = it.owner.display_name,
                authorImage = it.owner.profile_image
            )
        } ?: emptyList()
    }

    override suspend fun requestQuestionById(id: Int): Question {
        val questions = remoteDataSource.requestQuestionById(id)
        val question = questions?.items?.first()!!

        return Question(
            id = question.question_id,
            title = question.title,
            author = question.owner.display_name,
            authorImage = question.owner.profile_image,
            body = question.body
        )
    }

    override suspend fun requestAnswersByQuestionId(questionId: Int): List<Answer> {
        val answers = remoteDataSource.requestAnswersByQuestionId(questionId)

        return answers?.items?.map {
            Answer(
                id = it.answer_id,
                author = it.owner.display_name,
                authorImage = it.owner.profile_image,
                body = it.body
            )
        } ?: emptyList()
    }
}