package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Di

import com.m1guelgtz.templatecarsapi.Demo.Core.Di.AppConteiner
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBookDetailsUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases.GetBooksUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.BookDetailsViewModelFactory
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.ViewModels.LibraryViewModelFactory

class BooksModule (
    private val appConteiner: AppConteiner
) {
    private fun provideGetBookUseCase () : GetBooksUseCase {
        return GetBooksUseCase(appConteiner.booksRepository )
    }

    private fun provideGetBookDetailsUseCase () : GetBookDetailsUseCase {
        return GetBookDetailsUseCase(appConteiner.booksRepository)
    }

    fun provideBooksViewModelFactory () : LibraryViewModelFactory {
        return LibraryViewModelFactory (
            useCase = provideGetBookUseCase()
        )
    }

    fun provideBookDetailsViewModelFactory (bookId: Int, cachedBooks: List<Book>) : BookDetailsViewModelFactory {
        return BookDetailsViewModelFactory (
            useCase = provideGetBookDetailsUseCase(),
            bookId = bookId,
            cachedBooks = cachedBooks
        )
    }
}