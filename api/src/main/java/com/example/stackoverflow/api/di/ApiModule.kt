package com.example.stackoverflow.api.di

import com.example.stackoverflow.api.QuestionsApi
import com.example.stackoverflow.api.QuestionsRepositoryImpl
import com.example.stackoverflow.api.RemoteDataSource
import com.example.stackoverflow.api.RetrofitServer
import com.example.stackoverflow.questions.domain.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideQuestionsRepository(remoteDataSource: RemoteDataSource): QuestionsRepository {
        return QuestionsRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideApi(): QuestionsApi {
        return RetrofitServer.api
    }

}