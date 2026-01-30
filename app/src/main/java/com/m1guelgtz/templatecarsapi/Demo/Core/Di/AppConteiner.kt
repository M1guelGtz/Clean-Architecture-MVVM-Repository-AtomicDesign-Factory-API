package com.m1guelgtz.templatecarsapi.Demo.Core.Di

import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import retrofit2.Retrofit
import android.content.Context
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.Repository.BookRepositoryImplement
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Properties

class AppConteiner (context: Context) {
    private val baseUrl = loadBaseUrl(name = String())

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

    private fun loadBaseUrl(name: String): String {
        val properties = Properties()
        val inputStream = javaClass.classLoader?.getResourceAsStream("config.properties")
        inputStream?.use { properties.load(it) }
        return properties.getProperty("BASE_URL"+"search.json?q=${name}")
    }
}