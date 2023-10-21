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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.stackoverflow.questions.ui.viewmodels.AnswersScreenState
import com.example.stackoverflow.questions.ui.composes.ShowAuthorImage
import com.google.android.material.textview.MaterialTextView

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
                    Text("Author: " + state.question.author)
                    ShowAuthorImage(
                        modifier = Modifier.size(100.dp),
                        imageUrl = state.question.authorImage
                    )
                    Text("Title: ${state.question.title}")
                    if (state.question.body != null) {
                        ShowHtmlAnswerBody(body = state.question.body)
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                    )
                }
                items(state.answers) { question ->
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
}

@Composable
fun ShowHtmlAnswerBody(body: String) {
    val spannedBody = HtmlCompat.fromHtml(body, 0)

    AndroidView(
        factory = { MaterialTextView(it) },
        update = { it.text = spannedBody }
    )
}
