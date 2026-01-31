package com.m1guelgtz.templatecarsapi.Demo.Core.Network

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibrary {
    @GET("search.json")
    suspend fun getBooks(@Query("q") query: String): BookResponse
}
