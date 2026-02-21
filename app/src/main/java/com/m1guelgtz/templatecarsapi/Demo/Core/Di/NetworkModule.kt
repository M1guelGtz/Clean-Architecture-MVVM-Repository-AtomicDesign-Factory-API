package com.m1guelgtz.templatecarsapi.Demo.Core.Di

import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenLibrary(retrofit: Retrofit): OpenLibrary {
        return retrofit.create(OpenLibrary::class.java)
    }
}