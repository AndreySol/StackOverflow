package com.example.stackoverflow.presenter.screens.questions

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.stackoverflow.presenter.viewmodels.AnswersViewModel

internal const val questionIdArg = "question_id"

const val answersRoute = "answers_route"

fun NavGraphBuilder.answersScreen() {
    composable(
        route = "$answersRoute/{$questionIdArg}",
        arguments = listOf(
            navArgument(questionIdArg) {
                type = NavType.IntType
                nullable = false
            }
        )
    ) {
        val viewModel: AnswersViewModel = hiltViewModel()
        val state by viewModel.flow.collectAsState()
        AnswersScreen(state = state)
    }
}

fun NavController.navigateToAnswersScreen(questionId: Int) {
    navigate("$answersRoute/$questionId")
}

internal class QuestionIdArgs(val questionId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[questionIdArg]) as Int)
}