package com.example.stackoverflow.questions.ui.screens.answers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stackoverflow.answers.domain.entities.QuestionWithAnswers
import com.example.stackoverflow.common.ErrorCode
import com.example.stackoverflow.common.R
import com.example.stackoverflow.questions.domain.entities.Answer
import com.example.stackoverflow.questions.domain.entities.Question
import com.example.stackoverflow.questions.ui.composes.ShowAuthorImage
import com.example.stackoverflow.questions.ui.composes.ShowHtmlAnswerBody
import com.example.stackoverflow.questions.ui.screens.answers.viewmodel.AnswersScreenState

@Composable
fun AnswersScreen(state: AnswersScreenState) {

    state.run {
        when {
            loading -> ShowLoadingState()
            errorCode != null -> ShowErrorState(errorCode)
            questionWithAnswers != null -> ShowContent(questionWithAnswers)
        }
    }
}

@Composable
private fun ShowLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ShowErrorState(errorCode: ErrorCode) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: ${errorCode.toMessage(LocalContext.current)}",
            style = TextStyle(color = Color.Red)
        )
    }
}

@Composable
private fun ShowContent(questionWithAnswers: QuestionWithAnswers) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            Text("Author: " + questionWithAnswers.question.author)
            Spacer(modifier = Modifier.height(8.dp))
            ShowAuthorImage(
                modifier = Modifier.size(100.dp),
                imageUrl = questionWithAnswers.question.authorImage
            )
            Text("Title: ${questionWithAnswers.question.title}")
            if (questionWithAnswers.question.body != null) {
                ShowHtmlAnswerBody(body = questionWithAnswers.question.body!!)
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            )
        }
        if (questionWithAnswers.answers.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(LocalContext.current.getString(R.string.empty_answers_list))
                }
            }
        } else {
            items(questionWithAnswers.answers) { question ->
                Column(
                    modifier = Modifier.clickable {
                        //onItemClicked(question.id)
                    }
                ) {
                    Text("Author: ${question.author}")
                    ShowAuthorImage(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp),
                        imageUrl = question.authorImage
                    )
                    ShowHtmlAnswerBody(question.body)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnswersScreenEmptyList() {
    AnswersScreen(
        state = AnswersScreenState(
            loading = false,
            errorCode = null,
            questionWithAnswers = QuestionWithAnswers(
                question = Question(
                    id = 0,
                    body = "Body",
                    authorImage = "Image",
                    author = "Aurthor",
                    title = "Title"
                ),
                answers = emptyList()
            )
        )
    )
}

@Preview
@Composable
fun PreviewAnswersScreenError() {
    AnswersScreen(
        state = AnswersScreenState(
            loading = false,
            errorCode = ErrorCode.UNKNOWN_ERROR,
            questionWithAnswers = null
        )
    )
}

@Preview
@Composable
fun PreviewAnswersScreenOneAnswer() {
    AnswersScreen(
        state = AnswersScreenState(
            loading = false,
            errorCode = null,
            questionWithAnswers = QuestionWithAnswers(
                question = Question(
                    id = 0,
                    body = "Body",
                    authorImage = "Image",
                    author = "Aurthor",
                    title = "Title"
                ),
                answers = listOf(
                    Answer(
                        id = 0,
                        author = "Author",
                        authorImage = "Image",
                        body = "Body"
                    )
                )
            )
        )
    )
}
