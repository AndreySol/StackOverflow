package com.example.stackoverflow.questions.ui.screens.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.R
import com.example.stackoverflow.questions.domain.entities.Question
import com.example.stackoverflow.questions.ui.composes.ShowAuthorImage
import com.example.stackoverflow.questions.ui.screens.viewmodel.QuestionEvent
import com.example.stackoverflow.questions.ui.screens.viewmodel.QuestionsScreenState

@Composable
fun QuestionsScreen(
    state: QuestionsScreenState,
    onEventSent: (QuestionEvent) -> Unit,
    onNavigationRegistered: (QuestionsScreenState.Navigation) -> Unit
) {
    ObserveEvents(state, onNavigationRegistered)
    ObserveStates(state, onEventSent)
}

@Composable
private fun ObserveStates(
    state: QuestionsScreenState,
    onEventSent: (QuestionEvent) -> Unit
) {
    state.run {
        when {
            loading -> ShowLoading()
            errorCode != null -> ShowError(errorCode)
            questions.isNotEmpty() -> {
                ShowContent(questions = questions) { id ->
                    onEventSent(QuestionEvent.NavigateToAnswersById(id))
                }
            }

            else -> ShowEmptyContent()
        }
    }
}

@Composable
private fun ObserveEvents(
    state: QuestionsScreenState,
    onNavigationRegistered: (QuestionsScreenState.Navigation) -> Unit
) {
    LaunchedEffect(key1 = state) {
        if (state.navigateTo != null) {
            onNavigationRegistered(state.navigateTo)
        }
    }
}

@Composable
private fun ShowError(errorCode: ErrorCode) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error msg: ${errorCode.toMessage(LocalContext.current)}",
            style = TextStyle(color = Color.Red)
        )
    }
}

@Composable
private fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ShowContent(
    questions: List<Question>,
    onItemClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(questions) { question ->
            Column(
                modifier = Modifier.clickable {
                    onItemClicked(question.id)
                }
            ) {
                Text("Title: ${question.title}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Author: ${question.author}")
                Spacer(modifier = Modifier.height(8.dp))
                ShowAuthorImage(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    imageUrl = question.authorImage
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(1.dp)
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ShowEmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(LocalContext.current.getString(R.string.empty_questions_list))
    }
}

@Preview
@Composable
fun PreviewQuestionScreenEmpty() {
    QuestionsScreen(
        state = QuestionsScreenState(
            loading = false,
            errorCode = null,
            questions = emptyList()
        ),
        onNavigationRegistered = {},
        onEventSent = {}
    )
}

@Preview
@Composable
fun PreviewQuestionScreenError() {
    QuestionsScreen(
        state = QuestionsScreenState(
            loading = false,
            errorCode = ErrorCode.PARAMETER_ERROR,
            questions = emptyList()
        ),
        onNavigationRegistered = {},
        onEventSent = {}
    )
}

@Preview
@Composable
fun PreviewQuestionScreenOneItem() {
    QuestionsScreen(
        state = QuestionsScreenState(
            loading = false,
            errorCode = null,
            questions = listOf(
                Question(
                    0,
                    "Author",
                    "",
                    "Title",
                    "Body"
                )
            )
        ),
        onNavigationRegistered = {},
        onEventSent = {}
    )
}