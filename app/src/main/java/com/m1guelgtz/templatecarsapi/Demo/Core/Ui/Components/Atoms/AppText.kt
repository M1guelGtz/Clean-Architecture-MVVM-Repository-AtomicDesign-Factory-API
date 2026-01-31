package com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    maxLines: Int = 2
) {
    AppText(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    maxLines: Int = 1
) {
    AppText(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE
) {
    AppText(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}
