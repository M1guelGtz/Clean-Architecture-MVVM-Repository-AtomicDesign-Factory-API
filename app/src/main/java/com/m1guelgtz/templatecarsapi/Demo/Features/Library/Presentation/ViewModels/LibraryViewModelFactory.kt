package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBooksUseCase

class LibraryViewModelFactory (
    private val useCase: GetBooksUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LibraryViewModel(useCase) as T
    }
}