package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Components.Atoms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(height: Dp = 8.dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun HorizontalSpacer(width: Dp = 8.dp) {
    Spacer(modifier = Modifier.width(width))
}

// Spacers predefinidos
@Composable
fun SmallSpacer() = VerticalSpacer(4.dp)

@Composable
fun MediumSpacer() = VerticalSpacer(8.dp)

@Composable
fun LargeSpacer() = VerticalSpacer(16.dp)

@Composable
fun ExtraLargeSpacer() = VerticalSpacer(24.dp)
