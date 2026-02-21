package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Components.Organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.BookCover
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.SmallSpacer
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.SubtitleText
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.TitleText
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.BaseCard

@Composable
fun BookCard(
    title: String,
    modifier: Modifier = Modifier,
    authors: List<String> = emptyList(),
    year: Int? = null,
    editionCount: Int? = null,
    language: List<String> = emptyList(),
    coverId: Int? = null,
    rating: Float = 0f,
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
            verticalAlignment = Alignment.Top
        ) {
            BookCover(
                coverId = coverId,
                size = 70.dp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TitleText(
                    text = title,
                    maxLines = 2
                )
                SmallSpacer()
                
                // Mostrar rating con estrellas
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f", rating),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                
                if (authors.isNotEmpty()) {
                    SubtitleText(
                        text = "Autor: ${authors.joinToString(", ")}",
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                year?.let {
                    SubtitleText(
                        text = "Año: $it",
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                editionCount?.let {
                    SubtitleText(
                        text = "Ediciones: $it",
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                if (language.isNotEmpty()) {
                    SubtitleText(
                        text = "Idioma: ${language.take(2).joinToString(", ")}",
                        maxLines = 1
                    )
                }
            }
        }
    }
}
