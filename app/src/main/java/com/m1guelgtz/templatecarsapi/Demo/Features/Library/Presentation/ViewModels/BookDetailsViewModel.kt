package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBookDetailsUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.BookDetailsUIState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel @AssistedInject constructor(
    private val useCase: GetBookDetailsUseCase,
    @Assisted("bookId") private val bookId: Int,
    @Assisted("cachedBooks") private val cachedBooks: List<Book>
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("bookId") bookId: Int,
            @Assisted("cachedBooks") cachedBooks: List<Book>
        ): BookDetailsViewModel
    }
    
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