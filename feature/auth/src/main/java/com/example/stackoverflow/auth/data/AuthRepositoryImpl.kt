package com.example.stackoverflow.auth.data

import com.example.stackoverflow.auth.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return remoteDataSource.signIn(email, password)
    }
}