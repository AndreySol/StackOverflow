package com.example.stackoverflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.stackoverflow.questions.navigation.answersScreen
import com.example.stackoverflow.questions.navigation.navigateToAnswersScreen
import com.example.stackoverflow.questions.navigation.navigateToQuestionsScreen
import com.example.stackoverflow.questions.navigation.questionsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = mainRoute) {
        mainScreen(
            onButtonClicked = {
                navController.navigateToQuestionsScreen()
            }
        )
        questionsScreen(
            onItemClicked = { questionId ->
                navController.navigateToAnswersScreen(questionId)
            }
        )
        answersScreen()
    }
}