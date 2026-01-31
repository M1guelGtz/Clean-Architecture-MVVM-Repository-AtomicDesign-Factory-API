package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Mapper

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model.BookDTO
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book

fun BookDTO.toDomain(): Book {
    return Book(
        title = this.title,
        authors = this.authorName ?: emptyList(),
        firstPublishYear = this.firstPublishYear,
        editionCount = this.editionCount,
        language = this.language ?: emptyList(),
        coverId = this.coverId
    )
}