package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Di

import com.m1guelgtz.templatecarsapi.Demo.Core.Di.AppConteiner
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBooksUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModelFactory

class BooksModule (
    private val appConteiner: AppConteiner
) {
    private fun provideGetBookUseCase () : GetBooksUseCase {
        return GetBooksUseCase(appConteiner.booksRepository )
    }

    fun provideBooksViewModelFactory () : LibraryViewModelFactory {
        return LibraryViewModelFactory (
            useCase = provideGetBookUseCase()
        )
    }
}