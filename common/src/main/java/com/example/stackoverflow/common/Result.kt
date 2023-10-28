package com.example.stackoverflow.common

sealed interface Result<out T> {
    data class Success<T>(
        val data: T
    ) : Result<T>

    data class Failure(
        val errorCode: ErrorCode,
        val errorMsg: String? = null
    ) : Result<Nothing>

}
