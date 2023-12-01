package com.example.stackoverflow.network.di

import com.example.stackoverflow.network.QuestionsApi
import com.example.stackoverflow.network.RetrofitServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApi(): QuestionsApi {
        return RetrofitServer.api
    }

}