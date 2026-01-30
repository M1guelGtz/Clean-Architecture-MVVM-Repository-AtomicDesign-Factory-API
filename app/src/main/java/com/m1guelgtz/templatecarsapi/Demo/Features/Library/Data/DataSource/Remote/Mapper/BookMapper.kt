package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Mapper

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model.BookDTO
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book

fun BookDTO.toDomain (): Book {
    return Book (
        name = this.name
    )
}