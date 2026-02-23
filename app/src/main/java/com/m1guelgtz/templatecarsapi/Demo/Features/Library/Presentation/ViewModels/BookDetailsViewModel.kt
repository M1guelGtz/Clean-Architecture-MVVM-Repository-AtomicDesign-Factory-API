package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.m1guelgtz.templatecarsapi.Demo.Core.rutes.RutaDetalles
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBookDetailsUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.BookDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val useCase: GetBookDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val routeData = savedStateHandle.toRoute<RutaDetalles>()
    private val bookId = routeData.id
    
    private val _uiState = MutableStateFlow(BookDetailsUIState())
    val uiState = _uiState.asStateFlow()

    // En una arquitectura real, los libros vendrían del repositorio (caché)
    // Para mantener consistencia con tu código anterior, el NavHost proveerá la lista si es necesario
    // o el ViewModel la obtendrá de un flujo compartido.
    private var cachedBooks: List<Book> = emptyList()

    fun setCachedBooks(books: List<Book>) {
        cachedBooks = books
        loadBookDetails()
    }

    private fun loadBookDetails() {
        if (cachedBooks.isEmpty()) return
        
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