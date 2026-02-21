package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases

import javax.inject.Inject

sealed class GuessResult {
    data class Success(val message: String) : GuessResult()
    data class Near(val message: String) : GuessResult()
    data class Far(val message: String) : GuessResult()
    data class VeryFar(val message: String) : GuessResult()
}

class VerifyNumberUseCase @Inject constructor() {
    operator fun invoke(userNumber: Int, magicNumber: Int): GuessResult {
        val diff = kotlin.math.abs(userNumber - magicNumber)

        return when {
            diff == 0 -> GuessResult.Success("¡Exacto! ¡Ganaste!")
            diff <= 2 -> GuessResult.Near("¡Estás ardiendo! (Rango de 2)")
            diff <= 10 -> GuessResult.Near("¡Te estás calentando! (Rango de 10)")
            diff <= 20 -> GuessResult.Far("¡Tibio! (Rango de 20)")
            else -> GuessResult.VeryFar("¡Frío, muy frío!")
        }
    }
}