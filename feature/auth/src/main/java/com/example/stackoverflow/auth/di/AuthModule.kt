package com.example.stackoverflow.auth.di

import com.example.stackoverflow.auth.core.EmailValidationService
import com.example.stackoverflow.auth.core.EmailValidationServiceImpl
import com.example.stackoverflow.auth.data.AuthRepositoryImpl
import com.example.stackoverflow.auth.data.RemoteDataSource
import com.example.stackoverflow.auth.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideEmailValidatorService(
    ): EmailValidationService {
        return EmailValidationServiceImpl()
    }

    @Provides
    fun provideAuthRepository(
        remoteDataSource: RemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(remoteDataSource)
    }
}