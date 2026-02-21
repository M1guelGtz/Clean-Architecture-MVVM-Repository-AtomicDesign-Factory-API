package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Data.Repository

import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.Repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor() : GameRepository {
    override fun generateRandomNumber(): Int {
        return (1..100).random()
    }
}