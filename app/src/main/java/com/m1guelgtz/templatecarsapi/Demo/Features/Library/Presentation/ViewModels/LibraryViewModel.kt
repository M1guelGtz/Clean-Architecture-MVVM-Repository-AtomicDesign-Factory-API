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
    
    private var allBooks: List<Book> = emptyList()

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }
    
    fun toggleTop5Filter() {
        val newShowTop5 = !_uiState.value.showOnlyTop5
        _uiState.update { 
            it.copy(
                showOnlyTop5 = newShowTop5,
                book = if (newShowTop5) allBooks.take(5) else allBooks
            )
        }
    }

    fun searchBooks(query: String) {
        if (query.isEmpty()) {
            _uiState.update { it.copy(book = emptyList(), error = null) }
            allBooks = emptyList()
            return
        }

        _uiState.update { it.copy(searchQuery = query, isLoading = true, error = null) }
        viewModelScope.launch {
            val result = usecase(query)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { list ->
                        allBooks = list
                        val displayBooks = if (currentState.showOnlyTop5) list.take(5) else list
                        currentState.copy(isLoading = false, book = displayBooks)
                    },
                    onFailure = { error ->
                        allBooks = emptyList()
                        currentState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }
    }
}