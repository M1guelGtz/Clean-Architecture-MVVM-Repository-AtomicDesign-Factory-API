package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBookDetailsUseCase

class BookDetailsViewModelFactory(
    private val useCase: GetBookDetailsUseCase,
    private val bookId: Int,
    private val cachedBooks: List<Book>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookDetailsViewModel(useCase, bookId, cachedBooks) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
