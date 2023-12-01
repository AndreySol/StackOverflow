package com.example.stackoverflow.data.di

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
    fun provideQuestionsRepository(remoteDataSource: RemoteDataSource): QuestionsRepository {
        return QuestionsRepositoryImpl(remoteDataSource)
    }
}