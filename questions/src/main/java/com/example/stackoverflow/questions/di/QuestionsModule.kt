package com.example.stackoverflow.questions.di

import com.example.stackoverflow.questions.data.QuestionsRepositoryImpl
import com.example.stackoverflow.questions.data.RemoteDataSource
import com.example.stackoverflow.questions.data.api.QuestionsApi
import com.example.stackoverflow.questions.data.api.RetrofitServer
import com.example.stackoverflow.questions.domain.QuestionsRepository
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