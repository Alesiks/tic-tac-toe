package by.pupil.model

import java.util.*

data class GameResponse(
    private val gameStatus: GameStatus,
    private val board: Array<Array<Char>>,
    private val aiMove: WebCell?,
    private val winningSequence: List<WebCell>?
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