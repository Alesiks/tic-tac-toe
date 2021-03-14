package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

interface MovesFinder {
    fun getMoves(board: Board, player: Player): List<Move>
}