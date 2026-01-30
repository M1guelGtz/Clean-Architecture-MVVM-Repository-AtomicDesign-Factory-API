package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model

data class BookResponse(
    val results : List <BookDTO>
)

data class BookDTO (
    val name: String
    // Atributos reales
)
