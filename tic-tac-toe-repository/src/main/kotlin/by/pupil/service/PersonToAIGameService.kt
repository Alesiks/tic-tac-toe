package by.pupil.service

import by.pupil.domain.PersonToAIGame
import by.pupil.model.GameStatus
import by.pupil.query.GamesRepository
import java.time.LocalDateTime

class PersonToAIGameService(
    private val repository: GamesRepository,
) {
    fun startUserWithAIGame(
        userId: Int,
        minimaxDifficultyLevel: Int,
    ): Int {
        val personToAiGame =
            PersonToAIGame(
                userId,
                "minimax_$minimaxDifficultyLevel",
                GameStatus.GAME_CONTINUES,
                LocalDateTime.now(),
                null,
            )

        return repository.savePersonToAIGame(personToAiGame)
    }

    fun finishUserWithAIGame(
        gameId: Int,
        gameStatus: GameStatus,
    ) {
        repository.updateAIGame(gameId, LocalDateTime.now(), gameStatus)
    }
}
