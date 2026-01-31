package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Entities

data class Book(
    val title: String,
    val authors: List<String> = emptyList(),
    val firstPublishYear: Int? = null,
    val editionCount: Int? = null,
    val language: List<String> = emptyList(),
    val coverId: Int? = null,
    val rating: Float = 0f
)
