package com.example.stackoverflow.common

import android.content.Context

enum class ErrorCode {
    UNKNOWN_ERROR,
    UNKNOWN_HOST,
    PARAMETER_ERROR;

    fun toMessage(ctx: Context): String {
        return when (this) {
            UNKNOWN_HOST -> {
                ctx.getString(R.string.error_unknown_host)
            }

            UNKNOWN_ERROR -> {
                ctx.getString(R.string.error_unknown_error)
            }

            PARAMETER_ERROR -> {
                ctx.getString(R.string.error_parameter_error)
            }
        }
    }
}