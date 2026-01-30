package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book

interface BooksRepository {
    suspend fun getBooks (): List<Book>
}