package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.Repository

import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Mapper.toDomain
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository
import javax.inject.Inject

class BookRepositoryImplement @Inject constructor(
    private val openLibrary: OpenLibrary
) : BooksRepository {
    override suspend fun getBooks(name: String): List<Book> {
        return openLibrary.getBooks(name).docs.map { it.toDomain() }
    }
}