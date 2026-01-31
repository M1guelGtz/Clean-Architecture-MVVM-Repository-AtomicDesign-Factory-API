package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.Repository

import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Mapper.toDomain
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository

class BookRepositoryImplement (
    private val api : OpenLibrary
) : BooksRepository {
    override suspend fun getBooks(query: String): List<Book> {
        val response = api.getBooks(query)
        return response.results.map { it.toDomain() }
    }
}
