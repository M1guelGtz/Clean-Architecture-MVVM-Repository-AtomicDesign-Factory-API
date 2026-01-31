package com.m1guelgtz.templatecarsapi.Demo.Core.Di

import android.content.Context
import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.Repository.BookRepositoryImplement
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppConteiner (context: Context) {
    private val baseUrl = "https://openlibrary.org/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val openLibrary: OpenLibrary by lazy {
        retrofit.create(OpenLibrary::class.java)
    }

    val booksRepository : BooksRepository by lazy {
        BookRepositoryImplement(openLibrary)
    }
}
