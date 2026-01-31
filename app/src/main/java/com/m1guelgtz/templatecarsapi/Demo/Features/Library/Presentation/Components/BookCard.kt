package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.BookCover
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.SmallSpacer
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.SubtitleText
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Atoms.TitleText
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Molecules.BaseCard

@Composable
fun BookCard(
    title: String,
    modifier: Modifier = Modifier,
    authors: List<String> = emptyList(),
    year: Int? = null,
    editionCount: Int? = null,
    language: List<String> = emptyList(),
    coverId: Int? = null,
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
            // Portada del libro
            BookCover(
                coverId = coverId,
                size = 70.dp
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del libro
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TitleText(
                    text = title,
                    maxLines = 2
                )

                SmallSpacer()

                // Autores
                if (authors.isNotEmpty()) {
                    SubtitleText(
                        text = "Autor: ${authors.joinToString(", ")}",
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }

                // Año de publicación
                year?.let {
                    SubtitleText(
                        text = "Año: $it",
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }

                // Número de ediciones
                editionCount?.let {
                    SubtitleText(
                        text = "Ediciones: $it",
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }

                // Idiomas
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
