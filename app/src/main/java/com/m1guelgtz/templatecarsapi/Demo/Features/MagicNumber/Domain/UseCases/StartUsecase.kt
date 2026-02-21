package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases

import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.Repository.GameRepository
import javax.inject.Inject

class StartUsecase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(): Int = repository.generateRandomNumber()
}