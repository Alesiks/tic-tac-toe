package by.xxx.pupil.ai.minimax.evaluate

import by.xxx.pupil.winning.WinnerFinder
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

class DefaultStateEvaluator(private val winnerFinder: WinnerFinder) : StateEvaluator {

    override fun evaluate(board: Board, lastMove: Move): Int {
        return if (winnerFinder.isMoveLeadToWin(board, lastMove)) {
            if (Player.CROSSES == lastMove.player) {
                PERSON_WIN_SCORE
            } else {
                AI_WIN_SCORE
            }
        } else DRAW_SCORE
    }

    override fun evaluate(board: Board, lastMove: Move, hash: Long): Int {
        return evaluate(board, lastMove)
    }

    companion object {
        private const val AI_WIN_SCORE = 100
        private const val DRAW_SCORE = 0
        private const val PERSON_WIN_SCORE = -100
    }

}