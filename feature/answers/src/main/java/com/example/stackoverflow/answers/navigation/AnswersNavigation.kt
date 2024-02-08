package com.example.stackoverflow.questions.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.stackoverflow.questions.ui.screens.answers.AnswersScreen
import com.example.stackoverflow.questions.ui.screens.answers.viewmodel.AnswersViewModel

internal const val questionIdArg = "question_id"

const val answersRoute = "answers_route"

fun NavGraphBuilder.answersScreen() {

    composable(
        route = "${answersRoute}/{${questionIdArg}}",
        arguments = listOf(
            navArgument(questionIdArg) {
                type = NavType.IntType
                nullable = false
            }
        )
    ) {
        val viewModel: AnswersViewModel = hiltViewModel()
        val state by viewModel.flow.collectAsStateWithLifecycle()
        AnswersScreen(state = state)
    }
}

internal class QuestionIdArgs(val questionId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[questionIdArg]) as Int)
}

fun NavController.navigateToAnswersScreen(questionId: Int) {
    navigate("$answersRoute/$questionId")
}