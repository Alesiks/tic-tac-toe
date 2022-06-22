package by.pupil.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Users : Table(name = "users") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val lastActivityDateTime: Column<LocalDateTime> = datetime("last_activity_datetime")
    override val primaryKey = PrimaryKey(id)
}

object PersonToPersonGames : Table(name = "person_to_person_games") {
    val id: Column<Int> = integer("id").autoIncrement()
    val player1Id: Column<Int> = integer("player_1_id")
    val player2Id: Column<Int> = integer("player_2_id")
    val player1Mark: Column<String> = varchar("player_1_mark", 50)
    val player2Mark: Column<String> = varchar("player_2_mark", 50)
    val boardState: Column<String> = varchar("board_state", length = 1000)
    val gameResult: Column<String> = varchar("game_result", length = 50)
    val lastMoveDateTime: Column<LocalDateTime> = datetime("last_move_datetime")
    val nextMovePlayer: Column<String> = varchar("next_move_player", length = 50)
    override val primaryKey = PrimaryKey(Users.id)
}

object PersonToAiGames : Table(name = "person_to_ai_games") {
    val id: Column<Int> = integer("id").autoIncrement()
    val playerId: Column<Int> = integer("player_id")
    val aiAlgorithm: Column<String> = varchar("ai_algorithm", length = 50)
    val gameResult: Column<String> = varchar("game_result", length = 50)
    val gameStartDate: Column<LocalDateTime> = datetime("game_start_date")
    val gameEndDate: Column<LocalDateTime?> = datetime("game_end_date").nullable()
    override val primaryKey = PrimaryKey(Users.id)
}
