package com.example.stackoverflow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stackoverflow.database.entity.AnswerEntity
import com.example.stackoverflow.database.entity.AnswersEntity
import com.example.stackoverflow.database.entity.OwnerEntity
import com.example.stackoverflow.database.entity.QuestionEntity
import com.example.stackoverflow.database.entity.QuestionsEntity

/*@Database(
    entities = [
        AnswerEntity::class,
        AnswersEntity::class,
        OwnerEntity::class,
        QuestionEntity::class,
        QuestionsEntity::class
    ],
    version = 1
)*/
abstract class StackOverflowRoomDatabase : RoomDatabase() {
}