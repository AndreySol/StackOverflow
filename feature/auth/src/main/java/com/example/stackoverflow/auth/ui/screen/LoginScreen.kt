package com.example.stackoverflow.auth.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stackoverflow.auth.ui.viewmodel.LoginEvent
import com.example.stackoverflow.auth.ui.viewmodel.LoginState

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignedIn: () -> Unit,
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            isError = state.emailError,
            supportingText = {
                Text(text = state.emailErrorMsg)
            },
            value = state.email,
            onValueChange = { onEvent(LoginEvent.EmailValueChanged(it)) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            isError = state.passwordError,
            supportingText = {
                Text(text = state.passwordErrorMsg)
            },
            value = state.password,
            onValueChange = { onEvent(LoginEvent.PasswordValueChanged(it)) },
            label = { Text("Password") }
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
    )
}