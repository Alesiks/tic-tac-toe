package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

class DefenseMovesFinder(private val inRadiusMovesFinder: InRadiusMovesFinder) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = inRadiusMovesFinder.getMoves(board, player)
        TODO("Complete moves finder")
    }

}