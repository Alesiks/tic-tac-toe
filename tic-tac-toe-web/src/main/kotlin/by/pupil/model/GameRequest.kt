package by.pupil.model

import java.util.*

data class GameRequest(val board: Array<Array<Char>>, val playerMove: WebCell, val difficultyLevel: Int) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as GameRequest
        return difficultyLevel == that.difficultyLevel &&
            board.contentEquals(that.board) &&
            playerMove == that.playerMove
    }

    override fun hashCode(): Int {
        var result = Objects.hash(playerMove, difficultyLevel)
        result = 31 * result + Arrays.hashCode(board)
        return result
    }
}
