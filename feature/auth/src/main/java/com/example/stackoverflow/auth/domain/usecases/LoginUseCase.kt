package com.example.stackoverflow.auth.domain.usecases

import com.example.stackoverflow.auth.domain.AuthRepository
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        val result = repository.signIn(email, password)
        return if (result) {
            Result.Success(true)
        } else {
            Result.Failure(ErrorCode.PARAMETER_ERROR)
        }
    }
}