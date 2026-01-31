package com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    elevation: Int = 4,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = onClick ?: {}
    ) {
        content()
    }
}

@Composable
fun InfoCard(
    title: String,
    subtitle: String?,
    modifier: Modifier = Modifier,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    BaseCard(
        modifier = modifier.padding(8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leading?.invoke()
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.TitleText(
                    text = title,
                    maxLines = 1
                )
                subtitle?.let {
                    com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.SmallSpacer()
                    com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.SubtitleText(
                        text = it,
                        maxLines = 2
                    )
                }
            }
            
            trailing?.invoke()
        }
    }
}
