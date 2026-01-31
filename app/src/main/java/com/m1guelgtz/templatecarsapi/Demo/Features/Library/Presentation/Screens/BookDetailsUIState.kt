package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities.Book

data class BookDetailsUIState(
    val isLoading: Boolean = true,
    val book: Book? = null,
    val error: String? = null
)
