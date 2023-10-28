package com.example.stackoverflow.questions.data

import com.example.stackoverflow.questions.domain.QuestionsRepository
import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question
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

    override suspend fun requestQuestionById(id: Int): Question? {
        val questions = remoteDataSource.requestQuestionById(id)
        val question = questions?.items?.first()

        return question?.let {
            Question(
                id = it.question_id,
                title = it.title,
                author = it.owner.display_name,
                authorImage = it.owner.profile_image,
                body = it.body
            )
        }
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