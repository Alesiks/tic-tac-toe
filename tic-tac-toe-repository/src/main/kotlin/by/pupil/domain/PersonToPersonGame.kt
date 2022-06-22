package by.pupil.domain

import by.pupil.model.GameStatus
import by.pupil.model.MarkType
import java.time.LocalDateTime

data class PersonToPersonGame(
    val player1Id: Int,
    val player2Id: Int,
    val player1Mark: MarkType,
    val player2Mark: MarkType,
    val boardState: String,
    val gameResult: GameStatus,
    val lastMoveDateTime: LocalDateTime,
    val nextMovePlayer: MarkType
)
