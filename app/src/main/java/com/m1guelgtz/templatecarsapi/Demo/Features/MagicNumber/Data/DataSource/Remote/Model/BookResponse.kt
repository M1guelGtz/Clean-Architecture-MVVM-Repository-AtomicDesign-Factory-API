package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Data.DataSource.Remote.Model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("docs")
    val results: List<BookDTO>
)

data class BookDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("author_name")
    val authorName: List<String>? = null,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int? = null,
    @SerializedName("edition_count")
    val editionCount: Int? = null,
    @SerializedName("language")
    val language: List<String>? = null,
    @SerializedName("cover_i")
    val coverId: Int? = null
)
