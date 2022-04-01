package by.pupil.ai.minimax.findmoves

import by.pupil.model.Board
import by.pupil.model.Move
import by.pupil.model.Player

interface MovesFinder {
    fun getMoves(board: Board, player: Player): List<Move>
}