package com.example.stackoverflow.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.auth.core.EmailValidationService
import com.example.stackoverflow.auth.domain.usecases.LoginUseCase
import com.example.stackoverflow.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val emailValidator: EmailValidationService
) : ViewModel() {

    private val _flow = MutableStateFlow(LoginState())
    val flow: StateFlow<LoginState> = _flow

    private var emailError = true
    private var passwordError = true

    fun onEvent(event: LoginEvent) = when (event) {
        LoginEvent.SignIn -> signIn()
        is LoginEvent.EmailValueChanged -> changeEmail(event.name)
        is LoginEvent.PasswordValueChanged -> changePassword(event.password)
        LoginEvent.ErrorShown -> errorShown()
    }

    private fun changeEmail(email: String) {
        var emailErrorMsg = ""

        if (email.isEmpty()) {
            emailError = true
            emailErrorMsg = "Email should not be empty"
        } else if (!emailValidator.isValid(email)) {
            emailError = true
            emailErrorMsg = "Invalid email"
        } else {
            emailError = false
        }

        _flow.update {
            _flow.value.copy(
                email = email,
                emailError = emailError,
                emailErrorMsg = emailErrorMsg,
                submitEnabled = isSubmitEnabled()
            )
        }
    }

    private fun changePassword(password: String) {
        passwordError = password.isEmpty()
        val passwordErrorMsg = if (passwordError) "Password should not be empty" else ""

        _flow.update {
            _flow.value.copy(
                password = password,
                passwordError = passwordError,
                passwordErrorMsg = passwordErrorMsg,
                submitEnabled = isSubmitEnabled()
            )
        }
    }

    private fun signIn() {
        _flow.update {
            _flow.value.copy(loading = true)
        }
        viewModelScope.launch {
            val email = flow.value.email
            val password = flow.value.password

            when (val result = loginUseCase(email, password)) {
                is Result.Success -> {
                    _flow.update {
                        _flow.value.copy(
                            loading = false,
                            signedIn = true
                        )
                    }
                }

                is Result.Failure -> {
                    _flow.update {
                        _flow.value.copy(
                            loading = false,
                            errorMsg = "Sign In error"
                        )
                    }
                }
            }
        }
    }

    private fun errorShown() {
        _flow.update {
            _flow.value.copy(
                errorMsg = ""
            )
        }
    }

    private fun isSubmitEnabled(): Boolean {
        return !emailError && !passwordError
    }
}