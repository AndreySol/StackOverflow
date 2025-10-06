package com.example.stackoverflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.stackoverflow.answers.navigation.answersScreen
import com.example.stackoverflow.answers.navigation.navigateToAnswersScreen
import com.example.stackoverflow.questions.navigation.loginScreen
import com.example.stackoverflow.questions.navigation.navigateToLoginScreen
import com.example.stackoverflow.questions.navigation.navigateToQuestionsScreen
import com.example.stackoverflow.questions.navigation.questionsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = mainRoute) {

        loginScreen() {
            navController.navigateToQuestionsScreen()
        }

        mainScreen {
            navController.navigateToLoginScreen()
        }

        questionsScreen {
            navController.navigateToAnswersScreen(it)
        }

        answersScreen()
    }
}