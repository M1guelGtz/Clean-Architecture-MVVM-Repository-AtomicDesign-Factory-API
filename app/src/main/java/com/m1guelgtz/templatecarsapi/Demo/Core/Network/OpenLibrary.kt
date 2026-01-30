package com.m1guelgtz.templatecarsapi.Demo.Core.Network

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.DataSource.Remote.Model.BookResponse
import retrofit2.http.GET

interface OpenLibrary {
    @GET("Books")
    suspend fun getBooks(): BookResponse

}