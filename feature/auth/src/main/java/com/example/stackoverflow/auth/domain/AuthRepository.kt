package com.example.stackoverflow.auth.domain

import androidx.fragment.app.FragmentActivity

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun authenticateWithBiometric(activity: FragmentActivity): BiometricResult
    suspend fun isBiometricAvailable(): Boolean
    suspend fun isBiometricEnrolled(): Boolean
    suspend fun setBiometricEnabled(enabled: Boolean)
    suspend fun isBiometricEnabled(): Boolean
}