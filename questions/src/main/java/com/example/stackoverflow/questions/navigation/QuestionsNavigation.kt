package com.example.stackoverflow.questions.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackoverflow.questions.ui.screens.questions.QuestionsScreen
import com.example.stackoverflow.questions.ui.screens.questions.viewmodel.QuestionsScreenState
import com.example.stackoverflow.questions.ui.screens.questions.viewmodel.QuestionsViewModel

const val questionsRoute = "questions_route"

fun NavGraphBuilder.questionsScreen(navController: NavController) {
    composable(route = questionsRoute) {
        val viewModel: QuestionsViewModel = hiltViewModel()

        val state by viewModel.flow.collectAsState()

        QuestionsScreen(
            state = state,
            onEventSent = { event ->
                viewModel.onEvent(event)
            },
            onNavigationRegistered = { navigationEvent ->
                when (navigationEvent) {
                    is QuestionsScreenState.Navigation.ToAnswersById -> {
                        navController.navigateToAnswersScreen(navigationEvent.questionId)
                        viewModel.onNavigated()
                    }
                }
            }
        )
    }
}

fun NavController.navigateToQuestionsScreen() {
    navigate(questionsRoute)
}

