package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.BookCover
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.SmallSpacer
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.SubtitleText
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Atoms.TitleText
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.ErrorMessage
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.LoadingIndicator
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.BookDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesScreen(
    viewModel: BookDetailsViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Libro") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    LoadingIndicator()
                }
                uiState.error != null -> {
                    ErrorMessage(message = uiState.error ?: "Error desconocido")
                }
                uiState.book != null -> {
                    BookDetailsContent(
                        book = uiState.book!!,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun BookDetailsContent(
    book: com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Portada y título
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            BookCover(
                coverId = book.coverId,
                size = 120.dp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TitleText(
                    text = book.title,
                    maxLines = 5
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
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f / 5.0", book.rating),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                if (book.authors.isNotEmpty()) {
                    SubtitleText(
                        text = book.authors.joinToString(", "),
                        maxLines = 3
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Información adicional
        InfoSection(title = "Información General") {
            InfoItem(label = "Valoración", value = String.format("%.2f / 5.0 ⭐", book.rating))
            book.firstPublishYear?.let {
                InfoItem(label = "Primer año de publicación", value = it.toString())
            }
            book.editionCount?.let {
                InfoItem(label = "Número de ediciones", value = it.toString())
            }
            if (book.language.isNotEmpty()) {
                InfoItem(
                    label = "Idiomas disponibles",
                    value = book.language.joinToString(", ")
                )
            }
        }
    }
}

@Composable
private fun InfoSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun InfoItem(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}