package com.example.stackoverflow.auth.domain.usecases

import androidx.fragment.app.FragmentActivity
import com.example.stackoverflow.auth.domain.AuthRepository
import com.example.stackoverflow.auth.domain.BiometricResult
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import javax.inject.Inject

class BiometricAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun authenticate(activity: FragmentActivity): Result<BiometricResult> {
        return try {
            val result = authRepository.authenticateWithBiometric(activity)
            Result.Success(result)
        } catch (e: Exception) {
            Result.Failure(ErrorCode.UNKNOWN_ERROR, e.message)
        }
    }

    suspend fun isBiometricAvailable(): Result<Boolean> {
        return try {
            val isAvailable = authRepository.isBiometricAvailable()
            Result.Success(isAvailable)
        } catch (e: Exception) {
            Result.Failure(ErrorCode.UNKNOWN_ERROR, e.message)
        }
    }

    suspend fun isBiometricEnrolled(): Result<Boolean> {
        return try {
            val isEnrolled = authRepository.isBiometricEnrolled()
            Result.Success(isEnrolled)
        } catch (e: Exception) {
            Result.Failure(ErrorCode.UNKNOWN_ERROR, e.message)
        }
    }

    suspend fun setBiometricEnabled(enabled: Boolean): Result<Unit> {
        return try {
            authRepository.setBiometricEnabled(enabled)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(ErrorCode.UNKNOWN_ERROR, e.message)
        }
    }

    suspend fun isBiometricEnabled(): Result<Boolean> {
        return try {
            val isEnabled = authRepository.isBiometricEnabled()
            Result.Success(isEnabled)
        } catch (e: Exception) {
            Result.Failure(ErrorCode.UNKNOWN_ERROR, e.message)
        }
    }
}