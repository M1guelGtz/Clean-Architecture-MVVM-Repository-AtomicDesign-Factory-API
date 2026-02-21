package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Di

import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Data.Repository.GameRepositoryImpl
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.Repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MagicNumberModule {

    @Binds
    @Singleton
    abstract fun bindGameRepository(
        gameRepositoryImpl: GameRepositoryImpl
    ): GameRepository
}