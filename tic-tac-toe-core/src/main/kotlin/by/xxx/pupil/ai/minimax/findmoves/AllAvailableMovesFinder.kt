package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import by.xxx.pupil.model.isCellEmpty
import java.util.*

class AllAvailableMovesFinder : MovesFinder {
    override fun getMoves(board: Board, player: Player): List<Move> {
        val availableMoves: MutableList<Move> = ArrayList()
        for (i in 0 until board.height) {
            for (j in 0 until board.width) {
                if (isCellEmpty(board, i, j)) {
                    availableMoves.add(Move(i, j, player))
                }
            }
        }
        return availableMoves
    }
}