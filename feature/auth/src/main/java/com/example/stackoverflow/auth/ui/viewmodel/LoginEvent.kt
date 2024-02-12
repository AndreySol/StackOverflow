package com.example.stackoverflow.auth.ui.viewmodel

sealed interface LoginEvent {
    object SignIn: LoginEvent
    data class EmailValueChanged(val name: String) : LoginEvent
    data class PasswordValueChanged(val password: String) : LoginEvent
    object ErrorShown : LoginEvent
}