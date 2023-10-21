package com.example.stackoverflow.questions.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackoverflow.questions.ui.screens.QuestionsScreen
import com.example.stackoverflow.questions.ui.viewmodels.QuestionsViewModel

const val questionsRoute = "questions_route"

fun NavGraphBuilder.questionsScreen(onItemClicked: (Int) -> Unit) {
    composable(route = questionsRoute) {
        val viewModel: QuestionsViewModel = hiltViewModel()
        val state by viewModel.flow.collectAsState()
        QuestionsScreen(state = state) {
            onItemClicked(it)
        }
    }
}

fun NavController.navigateToQuestionsScreen() {
    navigate(questionsRoute)
}
