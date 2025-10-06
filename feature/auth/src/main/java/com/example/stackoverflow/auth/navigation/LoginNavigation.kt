package com.example.stackoverflow.questions.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackoverflow.auth.ui.screen.LoginScreen
import com.example.stackoverflow.auth.ui.viewmodel.LoginVewModel

const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen(onSignedIn: () -> Unit) {

    composable(route = loginRoute) {
        val viewModel: LoginVewModel = hiltViewModel()
        val state by viewModel.flow.collectAsStateWithLifecycle()
        LoginScreen(
            state,
            onEvent = {
                viewModel.onEvent(it)
            },
            onSignedIn = {
                onSignedIn()
            },
            onBiometricAuthenticate = { activity ->
                viewModel.authenticateWithBiometric(activity)
            }
        )
    }
}

fun NavController.navigateToLoginScreen() {
    navigate(loginRoute)
}