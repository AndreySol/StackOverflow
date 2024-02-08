package com.example.stackoverflow.data.di

import com.example.stackoverflow.answers.domain.AnswersRepository
import com.example.stackoverflow.data.AnswersRepositoryImpl
import com.example.stackoverflow.database.LocalDataSource
import com.example.stackoverflow.network.QuestionsRepositoryImpl
import com.example.stackoverflow.network.RemoteDataSource
import com.example.stackoverflow.questions.domain.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideQuestionsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): QuestionsRepository {
        return QuestionsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    fun provideAnswersRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): AnswersRepository {
        return AnswersRepositoryImpl(remoteDataSource, localDataSource)
    }
}