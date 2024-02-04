package by.pupil.web.model

import by.pupil.model.GameStatus
import java.util.*

data class GameResponse(
    val gameStatus: GameStatus,
    val board: Array<Array<Char>>,
    val aiMove: WebCell?,
    val winningSequence: List<WebCell>?
) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as GameResponse
        return gameStatus === that.gameStatus &&
            Arrays.equals(board, that.board) &&
            aiMove == that.aiMove &&
            winningSequence == that.winningSequence
    }

    override fun hashCode(): Int {
        var result = Objects.hash(gameStatus, aiMove, winningSequence)
        result = 31 * result + Arrays.hashCode(board)
        return result
    }

    override fun toString(): String {
        return "GameResponse{" +
            "gameState=" + gameStatus +
            ", board=" + Arrays.toString(board) +
            ", aiMove=" + aiMove +
            ", winningSequence=" + winningSequence +
            '}'
    }
}
