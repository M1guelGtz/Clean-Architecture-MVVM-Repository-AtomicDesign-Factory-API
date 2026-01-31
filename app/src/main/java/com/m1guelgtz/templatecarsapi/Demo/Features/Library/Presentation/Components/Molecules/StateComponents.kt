package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.AppText
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.TitleText

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    message: String? = null
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
        message?.let {
            AppText(text = it)
        }
    }
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TitleText(
            text = "Error: $message",
            color = Color.Red
        )
    }
}

@Composable
fun EmptyState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TitleText(
            text = message,
            color = Color.Gray
        )
    }
}
