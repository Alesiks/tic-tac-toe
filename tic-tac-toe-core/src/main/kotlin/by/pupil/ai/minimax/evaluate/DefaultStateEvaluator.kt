package by.pupil.ai.minimax.evaluate

import by.pupil.model.Board
import by.pupil.model.Move
import by.pupil.model.Player
import by.pupil.winning.WinnerFinder

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

    companion object {
        private const val AI_WIN_SCORE = 100
        private const val DRAW_SCORE = 0
        private const val PERSON_WIN_SCORE = -100
    }
}
