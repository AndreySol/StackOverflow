package com.example.stackoverflow.database.di

import android.content.Context
import androidx.room.Room
import com.example.stackoverflow.database.StackOverflowDB
import com.example.stackoverflow.database.dao.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideQuestionsRepository(
        @ApplicationContext appContext: Context
    ): StackOverflowDB {
        return Room.databaseBuilder(
            appContext,
            StackOverflowDB::class.java,
            "QuestionDataBase"
        ).build()
    }

    @Provides
    fun provideQuestionsDao(db: StackOverflowDB): QuestionDao {
        return db.questionDao
    }
}