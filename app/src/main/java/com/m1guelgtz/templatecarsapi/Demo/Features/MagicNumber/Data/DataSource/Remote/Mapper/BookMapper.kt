package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Data.DataSource.Remote.Mapper

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model.BookDTO
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book
import kotlin.random.Random

fun BookDTO.toDomain(): Book {
    return Book(
        title = this.title,
        authors = this.authorName ?: emptyList(),
        firstPublishYear = this.firstPublishYear,
        editionCount = this.editionCount,
        language = this.language ?: emptyList(),
        coverId = this.coverId,
        rating = Random.nextFloat() * 5f // Genera un número aleatorio entre 0.0 y 5.0
    )
}