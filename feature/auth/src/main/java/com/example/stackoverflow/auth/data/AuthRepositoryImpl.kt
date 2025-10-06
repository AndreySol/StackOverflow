package com.example.stackoverflow.auth.data

import androidx.fragment.app.FragmentActivity
import com.example.stackoverflow.auth.domain.AuthRepository
import com.example.stackoverflow.auth.domain.BiometricResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val biometricManager: BiometricManagerImpl,
    private val biometricPreferencesManager: BiometricPreferencesManager
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return remoteDataSource.signIn(email, password)
    }

    override suspend fun authenticateWithBiometric(activity: FragmentActivity): BiometricResult {
        return biometricManager.authenticate(activity)
    }

    override suspend fun isBiometricAvailable(): Boolean {
        return biometricManager.isBiometricAvailable()
    }

    override suspend fun isBiometricEnrolled(): Boolean {
        return biometricManager.isBiometricEnrolled()
    }

    override suspend fun setBiometricEnabled(enabled: Boolean) {
        biometricPreferencesManager.setBiometricEnabled(enabled)
    }

    override suspend fun isBiometricEnabled(): Boolean {
        return biometricPreferencesManager.isBiometricEnabled()
    }
}