package by.pupil.service

import by.pupil.domain.PersonToPersonGame
import by.pupil.model.*
import by.pupil.query.GamesRepository
import java.time.LocalDateTime

class PersonToPersonGameService(
    private val repository: GamesRepository
) {

    fun saveGame(player1: PersonPlayer, player2: PersonPlayer, board: Board, gameStatus: GameStatus, nextPlayerMove: MarkType) {
        val personToPersonGame = PersonToPersonGame(
            player1.id,
            player2.id,
            player1.markType,
            player2.markType,
            board.toString(),
            gameStatus,
            LocalDateTime.now(),
            nextPlayerMove
        )

        repository.savePersonsToPersonGame(personToPersonGame)
    }
}
