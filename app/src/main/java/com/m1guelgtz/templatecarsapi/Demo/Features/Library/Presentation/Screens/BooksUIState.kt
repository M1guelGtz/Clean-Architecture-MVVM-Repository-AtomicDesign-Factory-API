package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book

data class BooksUIState(
    val isLoading: Boolean = false,
    val book: List<Book> = emptyList(),
    val error: String? = null,
    val isRefreshing: Boolean = false
)
