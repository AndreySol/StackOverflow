package com.example.stackoverflow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackoverflow.ui.screens.MainScreen

const val mainRoute = "main_route"

fun NavGraphBuilder.navigateToMainScreen(onButtonClicked: () -> Unit) {
    composable(route = mainRoute) {
        MainScreen {
            onButtonClicked()
        }
    }
}

