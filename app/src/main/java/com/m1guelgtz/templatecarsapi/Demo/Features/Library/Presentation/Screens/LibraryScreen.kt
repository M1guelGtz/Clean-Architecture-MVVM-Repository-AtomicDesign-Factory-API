package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.EmptyState
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.ErrorMessage
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.LoadingIndicator
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Molecules.SearchBar
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Organisms.AppTopBar
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.Organisms.BookCard
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = "Open Library Search"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Search Bar
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.onSearchQueryChange(it) },
                onSearch = { viewModel.searchBooks(uiState.searchQuery) },
                modifier = Modifier.padding(16.dp),
                placeholder = "Buscar libros...",
                enabled = !uiState.isLoading
            )

            // Filtro Top 5
            if (uiState.book.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    FilterChip(
                        selected = uiState.showOnlyTop5,
                        onClick = { viewModel.toggleTop5Filter() },
                        label = { Text("Top 5") },
                        leadingIcon = if (uiState.showOnlyTop5) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Top 5",
                                    modifier = Modifier
                                )
                            }
                        } else null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${uiState.book.size} libros",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            // Content
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    uiState.isLoading -> {
                        LoadingIndicator()
                    }
                    uiState.error != null -> {
                        ErrorMessage(message = uiState.error ?: "Error desconocido")
                    }
                    uiState.book.isEmpty() && uiState.searchQuery.isNotEmpty() -> {
                        EmptyState(message = "No se encontraron libros")
                    }
                    uiState.book.isEmpty() -> {
                        EmptyState(message = "Ingresa un término de búsqueda")
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            itemsIndexed(uiState.book) { index, book ->
                                BookCard(
                                    title = book.title,
                                    authors = book.authors,
                                    year = book.firstPublishYear,
                                    editionCount = book.editionCount,
                                    language = book.language,
                                    coverId = book.coverId,
                                    rating = book.rating,
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        navController.navigate("detalles/$index")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
