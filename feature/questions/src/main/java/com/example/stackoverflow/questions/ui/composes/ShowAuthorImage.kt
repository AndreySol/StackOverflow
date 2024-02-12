package com.example.stackoverflow.questions.ui.composes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.stackoverflow.common.R

@Composable
fun ShowAuthorImage(
    modifier: Modifier,
    imageUrl: String
) {
    Box(
        modifier = modifier
    ) {
        val painter = rememberAsyncImagePainter(
            model = imageUrl,
        )
        val painterState = painter.state
        Image(painter = painter, contentDescription = null)
        if (painterState is AsyncImagePainter.State.Loading) {
            Image(
                painter = painterResource(id = R.drawable.image_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
        } else if (painterState is AsyncImagePainter.State.Error) {
            Image(
                painter = painterResource(id = R.drawable.image_error),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }
}