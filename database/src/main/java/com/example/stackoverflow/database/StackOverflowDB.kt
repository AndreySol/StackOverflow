package com.example.stackoverflow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stackoverflow.database.dao.QuestionDao
import com.example.stackoverflow.database.entity.QuestionEntity

@Database(
    entities = [
        QuestionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StackOverflowDB : RoomDatabase() {
    abstract val questionDao: QuestionDao
}