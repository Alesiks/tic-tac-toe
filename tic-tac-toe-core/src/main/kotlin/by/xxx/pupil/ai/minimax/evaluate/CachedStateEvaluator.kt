package by.xxx.pupil.ai.minimax.evaluate

import by.xxx.pupil.ai.hashing.ScoreCache
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.HashedBoard
import by.xxx.pupil.model.Move

class CachedStateEvaluator(
        private val originStateEvaluator: StateEvaluator,
        private val scoreCache: ScoreCache
) : StateEvaluator {

    override fun evaluate(board: Board, lastMove: Move): Int {
        if (board is HashedBoard) {
            var score = scoreCache.getScore(board)
            if (score == null) {
                score = originStateEvaluator.evaluate(board, lastMove)
                scoreCache.putScore(board, score)
            }
            return score;
        } else {
            return originStateEvaluator.evaluate(board, lastMove)
        }
    }

}