package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBooksUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.BooksUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val usecase: GetBooksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUIState())
    val uiState = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun searchBooks(query: String) {
        if (query.isEmpty()) {
            _uiState.update { it.copy(book = emptyList(), error = null) }
            return
        }

        _uiState.update { it.copy(searchQuery = query, isLoading = true, error = null) }
        viewModelScope.launch {
            val result = usecase(query)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { list ->
                        currentState.copy(isLoading = false, book = list)
                    },
                    onFailure = { error ->
                        currentState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }
    }
}