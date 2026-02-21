package com.m1guelgtz.templatecarsapi.Demo.Features.Library.Di

import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Data.Repository.BookRepositoryImplement
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Domain.Repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BooksHiltModule {

    @Binds
    @Singleton
    abstract fun bindBooksRepository(
        bookRepositoryImplement: BookRepositoryImplement
    ): BooksRepository
}