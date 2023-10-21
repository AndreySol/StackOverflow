package com.example.stackoverflow.presenter.screens.questions

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuestionsScreen(
    state: QuestionsScreenState,
    onItemClicked: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is QuestionsScreenState.Loaded -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    items(state.questions) { question ->
                        Column(
                            modifier = Modifier.clickable {
                                onItemClicked(question.id)
                            }
                        ) {
                            Text("Title: ${question.title}")
                            Text("Author: ${question.author}")
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

            QuestionsScreenState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}