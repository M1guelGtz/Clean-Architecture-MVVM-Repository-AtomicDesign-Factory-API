package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Di

import com.m1guelgtz.templatecarsapi.Demo.Core.Di.OpenLibraryRetrofit
import com.m1guelgtz.templatecarsapi.Demo.Core.Network.OpenLibrary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LibraryNetworkModule {

    @Provides
    @Singleton
    fun provideOpenLibraryApi(@OpenLibraryRetrofit retrofit: Retrofit): OpenLibrary {
        return retrofit.create(OpenLibrary::class.java)
    }
}