package com.example.stackoverflow.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Questions : Screen("questions_screen")
    object QuestionDetails : Screen("question_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
