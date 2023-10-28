package com.example.stackoverflow.questions.ui.screens

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.stackoverflow.questions.ui.composes.ShowAuthorImage
import com.example.stackoverflow.questions.ui.composes.ShowHtmlAnswerBody
import com.example.stackoverflow.questions.ui.viewmodels.AnswersScreenState

@Composable
fun AnswersScreen(state: AnswersScreenState) {
    when (state) {
        AnswersScreenState.Loading ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        is AnswersScreenState.Loaded -> {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                item {
                    Text("Author: " + state.questionWithAnswers.question.author)
                    ShowAuthorImage(
                        modifier = Modifier.size(100.dp),
                        imageUrl = state.questionWithAnswers.question.authorImage
                    )
                    Text("Title: ${state.questionWithAnswers.question.title}")
                    if (state.questionWithAnswers.question.body != null) {
                        ShowHtmlAnswerBody(body = state.questionWithAnswers.question.body)
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                    )
                }
                items(state.questionWithAnswers.answers) { question ->
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

        is AnswersScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${state.errorCode.toMessage()}",
                    style = TextStyle(color = Color.Red)
                )
            }
        }
    }
}
