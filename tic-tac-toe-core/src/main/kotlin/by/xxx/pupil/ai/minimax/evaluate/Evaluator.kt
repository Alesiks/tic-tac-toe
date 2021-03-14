package by.xxx.pupil.ai.minimax.evaluate

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move

interface Evaluator {
    fun evaluate(board: Board, lastMove: Move): Int
    fun evaluate(board: Board, lastMove: Move, hash: Long): Int
}