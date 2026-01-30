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

class LibraryViewModel (
    private val usecase : GetBooksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUIState())
    val uiState = _uiState.asStateFlow()
    init {
        loadBooks(name = "Soym")
    }
    private fun loadBooks (name: String){
        _uiState.update{ it.copy(isLoading = true) }
        //cambiare el estado filtrando datos por nombre
        viewModelScope.launch {
            val result = usecase(name)
            _uiState.update {
                curretState ->
                result.fold(
                    onSuccess = { list ->
                        curretState.copy(isLoading = false, book = list)
                    },
                    onFailure = { error ->
                        curretState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }

    }
}