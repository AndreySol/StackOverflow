package com.example.stackoverflow.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.auth.core.EmailValidationService
import com.example.stackoverflow.auth.domain.BiometricAuthState
import com.example.stackoverflow.auth.domain.BiometricResult
import com.example.stackoverflow.auth.domain.usecases.BiometricAuthUseCase
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
    private val biometricAuthUseCase: BiometricAuthUseCase,
    private val emailValidator: EmailValidationService
) : ViewModel() {

    private val _flow = MutableStateFlow(LoginState())
    val flow: StateFlow<LoginState> = _flow

    private var emailErrorMsg = "empty"
    private var passwordErrorMsg = "empty"

    init {
        checkBiometricAvailability()
    }

    fun onEvent(event: LoginEvent) = when (event) {
        LoginEvent.SignIn -> signIn()
        is LoginEvent.EmailValueChanged -> changeEmail(event.name)
        is LoginEvent.PasswordValueChanged -> changePassword(event.password)
        LoginEvent.ErrorShown -> errorShown()
        LoginEvent.BiometricAuthRequested -> requestBiometricAuth()
        LoginEvent.BiometricAuthCompleted -> biometricAuthCompleted()
        LoginEvent.CheckBiometricAvailability -> checkBiometricAvailability()
    }

    private fun changeEmail(email: String) {
        emailErrorMsg = validateEmail(email)

        _flow.update {
            _flow.value.copy(
                email = email,
                emailError = emailErrorMsg.isNotEmpty(),
                emailErrorMsg = emailErrorMsg,
                submitEnabled = isSubmitEnabled()
            )
        }
    }

    private fun validateEmail(email: String): String {
        return if (email.isEmpty()) {
            "Email should not be empty"
        } else if (!emailValidator.isValid(email)) {
            "Invalid email"
        } else {
            ""
        }
    }

    private fun changePassword(password: String) {
        passwordErrorMsg = if (password.isEmpty()) "Password should not be empty" else ""

        _flow.update {
            _flow.value.copy(
                password = password,
                passwordError = passwordErrorMsg.isNotEmpty(),
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

            when (loginUseCase(email, password)) {
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
        return emailErrorMsg.isEmpty() && passwordErrorMsg.isEmpty()
    }

    private fun checkBiometricAvailability() {
        viewModelScope.launch {
            val isAvailable = when (val result = biometricAuthUseCase.isBiometricAvailable()) {
                is Result.Success -> result.data
                is Result.Failure -> false
            }
            
            val isEnrolled = when (val result = biometricAuthUseCase.isBiometricEnrolled()) {
                is Result.Success -> result.data
                is Result.Failure -> false
            }

            biometricAuthUseCase.setBiometricEnabled(true)
            val isEnabled = when (val result = biometricAuthUseCase.isBiometricEnabled()) {
                is Result.Success -> result.data
                is Result.Failure -> false
            }

            _flow.update {
                it.copy(
                    biometricAuthState = BiometricAuthState(
                        isAvailable = isAvailable,
                        isEnrolled = isEnrolled,
                        isEnabled = isEnabled
                    )
                )
            }
        }
    }

    private fun requestBiometricAuth() {
        _flow.update {
            it.copy(
                biometricAuthState = it.biometricAuthState.copy(showPrompt = true)
            )
        }
    }

    fun handleBiometricResult(result: BiometricResult) {
        when (result) {
            BiometricResult.Success -> {
                _flow.update {
                    it.copy(
                        signedIn = true,
                        biometricAuthState = it.biometricAuthState.copy(
                            showPrompt = false,
                            result = result
                        )
                    )
                }
            }
            else -> {
                _flow.update {
                    it.copy(
                        biometricAuthState = it.biometricAuthState.copy(
                            showPrompt = false,
                            result = result
                        ),
                        errorMsg = when (result) {
                            BiometricResult.Failed -> "Biometric authentication failed"
                            BiometricResult.Cancelled -> "Biometric authentication cancelled"
                            BiometricResult.NotAvailable -> "Biometric authentication not available"
                            BiometricResult.NotEnrolled -> "No biometric credentials enrolled"
                            BiometricResult.ErrorLockout -> "Too many failed attempts"
                            BiometricResult.ErrorLockoutPermanent -> "Biometric authentication locked"
                            is BiometricResult.Error -> result.errorMessage
                            else -> "Unknown biometric error"
                        }
                    )
                }
            }
        }
    }

    private fun biometricAuthCompleted() {
        _flow.update {
            it.copy(
                biometricAuthState = it.biometricAuthState.copy(
                    showPrompt = false,
                    result = null
                )
            )
        }
    }

    fun authenticateWithBiometric(activity: androidx.fragment.app.FragmentActivity) {
        viewModelScope.launch {
            when (val result = biometricAuthUseCase.authenticate(activity)) {
                is Result.Success -> {
                    handleBiometricResult(result.data)
                }
                is Result.Failure -> {
                    handleBiometricResult(BiometricResult.Error(-1, result.errorMsg ?: "Unknown error"))
                }
            }
        }
    }
}