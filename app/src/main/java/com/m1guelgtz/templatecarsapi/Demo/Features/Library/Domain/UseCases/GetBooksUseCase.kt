package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.UseCases

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository

class GetBooksUseCase (
    private val repository: BooksRepository
) {
}