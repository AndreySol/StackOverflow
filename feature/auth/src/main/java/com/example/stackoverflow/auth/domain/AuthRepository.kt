package com.example.stackoverflow.auth.domain

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Boolean
}