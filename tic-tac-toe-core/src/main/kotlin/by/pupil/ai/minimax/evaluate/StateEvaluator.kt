package by.pupil.ai.minimax.evaluate

import by.pupil.model.Board
import by.pupil.model.Move

interface StateEvaluator {

    fun evaluate(board: Board, lastMove: Move): Int

}