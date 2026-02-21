package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases.StartUsecase
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases.VerifyNumberUseCase

class GameViewModelFactory(
    private val verifyNumberUseCase: VerifyNumberUseCase,
    private val startUseCase: StartUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(verifyNumberUseCase, startUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}