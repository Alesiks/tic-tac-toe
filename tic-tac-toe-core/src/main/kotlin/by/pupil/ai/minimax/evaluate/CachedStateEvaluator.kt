package by.pupil.ai.minimax.evaluate

import by.pupil.ai.hashing.ScoreCache
import by.pupil.model.Board
import by.pupil.model.HashedBoard
import by.pupil.model.Move

class CachedStateEvaluator(
    private val originStateEvaluator: StateEvaluator,
    private val scoreCache: ScoreCache,
) : StateEvaluator {
    override fun evaluate(
        board: Board,
        lastMove: Move,
    ): Int {
        if (board is HashedBoard) {
            var score = scoreCache.getScore(board)
            if (score == null) {
                score = originStateEvaluator.evaluate(board, lastMove)
                scoreCache.putScore(board, score)
            }
            return score
        } else {
            return originStateEvaluator.evaluate(board, lastMove)
        }
    }
}
