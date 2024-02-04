package by.pupil.domain

import by.pupil.model.GameStatus
import java.time.LocalDateTime

data class PersonToAIGame(
    val playerId: Int,
    val aiAlgorithm: String,
    val gameResult: GameStatus,
    val gameStartDate: LocalDateTime,
    val gameEndDate: LocalDateTime?,
)
