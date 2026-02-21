package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases.GuessResult
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases.StartUsecase
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Domain.UseCases.VerifyNumberUseCase
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Screens.GameState
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Screens.GameStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val verifyNumberUseCase: VerifyNumberUseCase,
    private val startUseCase: StartUsecase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameState())
    val uiState = _uiState.asStateFlow()

    init {
        startGame()
    }

    fun startGame() {
        _uiState.update {
            it.copy(
                magicNumber = startUseCase(),
                userNumber = "",
                message = "¡Adivina el número entre 1 y 100!",
                lives = 5,
                status = GameStatus.PLAYING
            )
        }
    }

    fun onUserNumberChange(newValue: String) {
        if (newValue.isEmpty() || (newValue.all { it.isDigit() } && newValue.length <= 3)) {
            _uiState.update { it.copy(userNumber = newValue) }
        }
    }

    fun checkNumber() {
        val currentState = _uiState.value
        if (currentState.status != GameStatus.PLAYING) return
        
        val guess = currentState.userNumber.toIntOrNull()
        if (guess == null) {
            _uiState.update { it.copy(message = "Por favor, ingresa un número válido") }
            return
        }

        val result = verifyNumberUseCase(guess, currentState.magicNumber)
        
        _uiState.update { state ->
            when (result) {
                is GuessResult.Success -> state.copy(
                    message = result.message,
                    status = GameStatus.WON,
                    userNumber = ""
                )
                else -> {
                    val newLives = state.lives - 1
                    val baseMessage = when(result) {
                        is GuessResult.Near -> result.message
                        is GuessResult.Far -> result.message
                        is GuessResult.VeryFar -> result.message
                        else -> "Sigue intentando"
                    }
                    
                    if (newLives <= 0) {
                        state.copy(
                            message = "¡Perdiste! El número era ${state.magicNumber}",
                            lives = 0,
                            status = GameStatus.LOST,
                            userNumber = ""
                        )
                    } else {
                        state.copy(
                            message = baseMessage,
                            lives = newLives,
                            userNumber = ""
                        )
                    }
                }
            }
        }
    }
}