package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBookDetailsUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.BookDetailsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val useCase: GetBookDetailsUseCase,
    private val bookId: Int,
    private val cachedBooks: List<Book>
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(BookDetailsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBookDetails()
    }

    private fun loadBookDetails() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            val result = useCase(bookId, cachedBooks)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { book ->
                        currentState.copy(isLoading = false, book = book)
                    },
                    onFailure = { error ->
                        currentState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }
    }

    fun retry() {
        loadBookDetails()
    }
}
