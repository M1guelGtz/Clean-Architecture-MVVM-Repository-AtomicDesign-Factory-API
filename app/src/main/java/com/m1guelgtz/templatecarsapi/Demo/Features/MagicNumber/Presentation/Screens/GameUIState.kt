package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Screens

data class GameState(
    val magicNumber: Int = 0,
    val userNumber: String = "",
    val message: String = "¡Suerte!",
    val lives: Int = 5,
    val status: GameStatus = GameStatus.IDLE
)

enum class GameStatus {
    IDLE,
    PLAYING,
    WON,
    LOST
}