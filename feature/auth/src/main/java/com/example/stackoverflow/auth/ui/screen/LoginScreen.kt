package com.example.stackoverflow.auth.ui.screen

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.stackoverflow.auth.ui.viewmodel.LoginEvent
import com.example.stackoverflow.auth.ui.viewmodel.LoginState

// Helper function to find FragmentActivity from any context
private fun Context.findFragmentActivity(): FragmentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is FragmentActivity) return context
        context = context.baseContext
    }
    return null
}

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignedIn: () -> Unit,
    onBiometricAuthenticate: ((FragmentActivity) -> Unit)? = null
) {
    val context = LocalContext.current

    if (state.signedIn) {
        onSignedIn()
    }

    LaunchedEffect(key1 = state.errorMsg) {
        if (state.errorMsg.isNotBlank()) {
            Toast.makeText(context, state.errorMsg, Toast.LENGTH_SHORT).show()
            onEvent(LoginEvent.ErrorShown)
        }
    }

    // Handle biometric prompt
    LaunchedEffect(key1 = state.biometricAuthState.showPrompt) {
        if (state.biometricAuthState.showPrompt && onBiometricAuthenticate != null) {
            val fragmentActivity = context.findFragmentActivity()
            if (fragmentActivity != null) {
                onBiometricAuthenticate(fragmentActivity)
            } else {
                // Handle case where FragmentActivity is not found
                // Show error message via Toast and reset prompt state
                Toast.makeText(
                    context,
                    "Biometric authentication not available in this context",
                    Toast.LENGTH_SHORT
                ).show()
                onEvent(LoginEvent.BiometricAuthCompleted)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            isError = state.emailError,
            supportingText = {
                Text(text = state.emailErrorMsg)
            },
            value = state.email,
            onValueChange = { onEvent(LoginEvent.EmailValueChanged(it)) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            isError = state.passwordError,
            supportingText = {
                Text(text = state.passwordErrorMsg)
            },
            value = state.password,
            onValueChange = { onEvent(LoginEvent.PasswordValueChanged(it)) },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            enabled = state.submitEnabled && !state.loading,
            modifier = Modifier.width(200.dp),
            onClick = {
                onEvent(LoginEvent.SignIn)
            }
        ) {
            if (state.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp)
                )
            } else {
                Text(
                    text = "Sign in",
                    style = TextStyle(fontSize = 22.sp)
                )
            }
        }

        if (state.biometricAuthState.isAvailable && state.biometricAuthState.isEnrolled) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "or")
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                enabled = !state.loading,
                modifier = Modifier.width(200.dp),
                onClick = { onEvent(LoginEvent.BiometricAuthRequested) }
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Biometric Authentication",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Use Biometric",
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    val state = LoginState()
    LoginScreen(
        state,
        onEvent = {},
        onSignedIn = {},
        onBiometricAuthenticate = {}
    )
}

@Composable
@Preview
fun LoginScreenPreviewLoading() {
    val state = LoginState(loading = true)
    LoginScreen(
        state,
        onEvent = {},
        onSignedIn = {},
        onBiometricAuthenticate = {}
    )
}