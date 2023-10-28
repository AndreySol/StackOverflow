package com.example.stackoverflow.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

enum class ErrorCode {
    UNKNOWN_ERROR,
    UNKNOWN_HOST,
    PARAMETER_ERROR;

    @Composable
    fun toMessage(): String {
        return when (this) {
            UNKNOWN_HOST -> {
                stringResource(R.string.error_unknown_host)
            }

            UNKNOWN_ERROR -> {
                stringResource(R.string.error_unknown_error)
            }

            PARAMETER_ERROR -> {
                stringResource(R.string.error_parameter_error)
            }
        }
    }
}