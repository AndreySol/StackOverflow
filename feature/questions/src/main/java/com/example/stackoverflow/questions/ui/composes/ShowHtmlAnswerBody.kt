package com.example.stackoverflow.questions.ui.composes

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView

@Composable
fun ShowHtmlAnswerBody(body: String) {
    val spannedBody = HtmlCompat.fromHtml(body, 0)

    AndroidView(
        factory = { MaterialTextView(it) },
        update = { it.text = spannedBody }
    )
}