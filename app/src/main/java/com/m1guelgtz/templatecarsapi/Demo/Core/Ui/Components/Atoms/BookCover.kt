package com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun BookCover(
    coverId: Int?,
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier
            .size(width = size, height = size * 1.4f)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        if (coverId != null) {
            AsyncImage(
                model = "https://covers.openlibrary.org/b/id/$coverId-M.jpg",
                contentDescription = "Portada del libro",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
