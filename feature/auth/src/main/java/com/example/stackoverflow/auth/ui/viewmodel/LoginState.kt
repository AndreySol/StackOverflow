package com.example.stackoverflow.auth.ui.viewmodel

import com.example.stackoverflow.auth.domain.BiometricAuthState

data class LoginState(
    val errorMsg: String = "",

    val email: String = "",
    val emailError: Boolean = false,
    val emailErrorMsg: String = "",

    val password: String = "",
    val passwordError: Boolean = false,
    val passwordErrorMsg: String = "",

    val submitEnabled: Boolean = false,

    val loading: Boolean = false,

    val signedIn: Boolean = false,

    val biometricAuthState: BiometricAuthState = BiometricAuthState()
)