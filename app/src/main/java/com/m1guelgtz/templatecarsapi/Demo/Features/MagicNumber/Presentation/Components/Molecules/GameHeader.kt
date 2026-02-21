package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Components.Molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Components.Atoms.AppText

@Composable
fun GameHeader(
    lives: Int,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = "Vidas: $lives",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (lives <= 2) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppText(
            text = message,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}