package com.example.stackoverflow.auth.domain

data class BiometricAuthState(
    val isAvailable: Boolean = false,
    val isEnrolled: Boolean = false,
    val isEnabled: Boolean = false,
    val showPrompt: Boolean = false,
    val result: BiometricResult? = null
)