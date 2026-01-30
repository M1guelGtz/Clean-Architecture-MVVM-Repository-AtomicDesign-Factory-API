package com.m1guelgtz.templatecarsapi.Demo.Core.Di

import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import retrofit2.Retrofit
import kotlin.getValue
import android.content.Context
import retrofit2.converter.gson.GsonConverterFactory

class AppConteiner {
    private val retrofit: Retrofit = Retrofit.Builder()
        //.baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val openLibrary: OpenLibrary by lazy {
        retrofit.create(OpenLibrary::class.java)
    }
}