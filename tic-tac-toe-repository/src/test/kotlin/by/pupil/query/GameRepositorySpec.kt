package by.pupil.query

import by.pupil.db.DbSettings
import by.pupil.domain.PersonToAIGame
import by.pupil.model.GameStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.time.Month

class GameRepositorySpec : FunSpec({

    val gamesRepository = GamesRepository(DbSettings)

    beforeTest {
        gamesRepository.init()
    }

    test("save user") {
        val userId = gamesRepository.saveUser("Ales")

        val user = gamesRepository.getUserById(userId)
        user.name shouldBe "Ales"
    }

    test("update user last activity") {
        val userId = gamesRepository.saveUser("Ales")
        val date = LocalDateTime.of(1977, Month.MAY, 4, 12, 30, 0)
        gamesRepository.updateLastUserActivityUser(userId, date)

        val user = gamesRepository.getUserById(userId)
        user.name shouldBe "Ales"
        user.lastActivityDateTime shouldBe date
    }

    test("save person to AI game") {
        val gameId = gamesRepository.savePersonToAIGame(
            PersonToAIGame(
                12,
                "minimax_2",
                GameStatus.GAME_CONTINUES,
                LocalDateTime.of(1977, Month.MAY, 4, 12, 30, 0),
                null
            )
        )

        val actualGame = gamesRepository.getPersonToAIGameById(gameId)
        actualGame.playerId shouldBe 12
        actualGame.aiAlgorithm shouldBe "minimax_2"
        actualGame.gameResult shouldBe GameStatus.GAME_CONTINUES
        actualGame.gameStartDate shouldBe LocalDateTime.of(1977, Month.MAY, 4, 12, 30, 0)
        actualGame.gameEndDate shouldBe null
    }
})
