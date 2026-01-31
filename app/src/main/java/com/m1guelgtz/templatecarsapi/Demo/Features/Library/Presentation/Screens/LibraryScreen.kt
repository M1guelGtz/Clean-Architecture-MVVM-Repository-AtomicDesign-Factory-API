package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Molecules.EmptyState
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Molecules.ErrorMessage
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Molecules.LoadingIndicator
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Molecules.SearchBar
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.Components.Organisms.AppTopBar
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Components.BookCard
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    factory: LibraryViewModelFactory
) {
    val viewModel: LibraryViewModel = viewModel(factory = factory)
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
                            items(uiState.book) { book ->
                                BookCard(
                                    title = book.title,
                                    authors = book.authors,
                                    year = book.firstPublishYear,
                                    editionCount = book.editionCount,
                                    language = book.language,
                                    coverId = book.coverId,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
