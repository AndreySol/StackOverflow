package com.example.stackoverflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stackoverflow.presenter.screens.MainScreen
import com.example.stackoverflow.presenter.screens.questions.answersScreen
import com.example.stackoverflow.presenter.screens.questions.navigateToAnswersScreen
import com.example.stackoverflow.presenter.screens.questions.navigateToQuestionsScreen
import com.example.stackoverflow.presenter.screens.questions.questionsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen {
                navController.navigateToQuestionsScreen()
            }
        }
        questionsScreen(
            onItemClicked = {questionId ->
                navController.navigateToAnswersScreen(questionId)
            }
        )
        answersScreen()
    }
}