package by.pupil.query

import by.pupil.db.DbSettings
import by.pupil.domain.PersonToAIGame
import by.pupil.domain.PersonToPersonGame
import by.pupil.domain.User
import by.pupil.model.GameStatus
import by.pupil.model.PersonToAiGames
import by.pupil.model.PersonToPersonGames
import by.pupil.model.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime
import java.time.LocalDateTime.now

class GamesRepository(private val dbSettings: DbSettings) {
    fun init() {
        transaction(dbSettings.db) {
            SchemaUtils.create(Users, PersonToPersonGames, PersonToAiGames)
        }
    }

    fun saveUser(userName: String): Int {
        var userId = -1
        transaction(dbSettings.db) {
            userId = Users.insert {
                it[name] = userName
                it[lastActivityDateTime] = now()
            } get Users.id
        }
        return userId
    }

    fun getUserById(id: Int): User {
        return transaction(dbSettings.db) {
            Users.select(Users.id eq id).map { User(it[Users.id], it[Users.name], it[Users.lastActivityDateTime]) }
                .first()
        }
    }

    fun updateLastUserActivityUser(
        id: Int,
        dateTime: LocalDateTime,
    ) {
        transaction(dbSettings.db) {
            Users.update({ Users.id eq id }) {
                it[lastActivityDateTime] = dateTime
            }
        }
    }

    fun savePersonsToPersonGame(personToPersonGame: PersonToPersonGame) {
        transaction(dbSettings.db) {
            PersonToPersonGames.insert {
                it[player1Id] = personToPersonGame.player1Id
                it[player2Id] = personToPersonGame.player2Id
                it[player1Mark] = personToPersonGame.player1Mark.name
                it[player2Mark] = personToPersonGame.player2Mark.name
                it[boardState] = personToPersonGame.boardState
                it[gameResult] = personToPersonGame.gameResult.name
                it[lastMoveDateTime] = personToPersonGame.lastMoveDateTime
                it[nextMovePlayer] = personToPersonGame.nextMovePlayer.name
            }
        }
    }

    fun savePersonToAIGame(game: PersonToAIGame): Int {
        var gameId = -1
        transaction(dbSettings.db) {
            gameId = PersonToAiGames.insert {
                it[playerId] = game.playerId
                it[aiAlgorithm] = game.aiAlgorithm
                it[gameStartDate] = game.gameStartDate
                it[gameEndDate] = game.gameEndDate
                it[gameResult] = game.gameResult.name
            } get PersonToAiGames.id
        }
        return gameId
    }

    fun getPersonToAIGameById(gameId: Int): PersonToAIGame {
        return transaction(dbSettings.db) {
            PersonToAiGames.select(PersonToAiGames.id eq gameId).map {
                PersonToAIGame(
                    it[PersonToAiGames.playerId],
                    it[PersonToAiGames.aiAlgorithm],
                    GameStatus.valueOf(it[PersonToAiGames.gameResult]),
                    it[PersonToAiGames.gameStartDate],
                    it[PersonToAiGames.gameEndDate],
                )
            }
                .first()
        }
    }

    fun updateAIGame(
        id: Int,
        endDate: LocalDateTime,
        result: GameStatus,
    ) {
        transaction(dbSettings.db) {
            PersonToAiGames.update({ PersonToAiGames.id eq id }) {
                it[gameEndDate] = endDate
                it[gameResult] = result.name
            }
        }
    }
}
