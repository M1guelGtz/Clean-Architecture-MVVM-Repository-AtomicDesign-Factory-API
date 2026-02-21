package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("numFound") val numFound: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("numFoundExact") val numFoundExact: Boolean,
    @SerializedName("docs") val docs: List<BookDTO>
)

data class BookDTO(
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorName: List<String>?,
    @SerializedName("first_publish_year") val firstPublishYear: Int?,
    @SerializedName("edition_count") val editionCount: Int,
    @SerializedName("language") val language: List<String>?,
    @SerializedName("cover_i") val coverId: Int?
)
