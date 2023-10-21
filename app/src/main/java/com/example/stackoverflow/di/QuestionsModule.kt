package com.example.stackoverflow.di

import com.example.stackoverflow.data.questions.QuestionsRepositoryImpl
import com.example.stackoverflow.data.questions.RemoteDataSource
import com.example.stackoverflow.data.questionsapi.QuestionsApi
import com.example.stackoverflow.data.questionsapi.RetrofitServer
import com.example.stackoverflow.domain.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuestionsModule {

    @Provides
    fun provideQuestionsRepository(remoteDataSource: RemoteDataSource): QuestionsRepository {
        return QuestionsRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideApi(): QuestionsApi {
        return RetrofitServer.api
    }

}