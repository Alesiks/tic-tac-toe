package by.pupil.ai.minimax.findmoves

import by.pupil.model.Board
import by.pupil.model.Move
import by.pupil.model.Player
import by.pupil.model.isCellEmpty

class AllAvailableMovesFinder : MovesFinder {
    override fun getMoves(
        board: Board,
        player: Player,
    ): List<Move> {
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
