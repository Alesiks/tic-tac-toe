package by.xxx.pupil.ai.minimax

import by.xxx.pupil.ai.hashing.ZobristHashing
import by.xxx.pupil.ai.minimax.evaluate.StateEvaluator
import by.xxx.pupil.ai.minimax.findmoves.MovesFinder
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Player
import by.xxx.pupil.model.getCorrespondingCellType
import by.xxx.pupil.model.getRival
import by.xxx.pupil.winning.WinnerFinder
import kotlin.math.max
import kotlin.math.min

class Minimax(
        private val movesFinder: MovesFinder,
        private val evaluator: StateEvaluator,
        private val winnerFinder: WinnerFinder,
        private val zobristHashing: ZobristHashing
) {

    /**
     * @param board              - describes current game state
     * @param currDepth          - current level in game tree which is build using minimax algorithm
     * @param maxDepth           - max level in game tree, after reaching which the tree traverse stops
     * @param isMaximizingPlayer - is current player maximize score or minimize
     * @param alpha              - the best value that the maximizer currently can guarantee at that level or above.
     * @param beta               - the best value that the minimizer currently can guarantee at that level or above.
     * @param player             - current player
     * @param currHash           - Zobrist hash for current board state
     * @return min or max (depends on player type) value that minimax can achieve on that level
     */
    fun minimax(
            board: Board,
            currDepth: Int,
            maxDepth: Int,
            isMaximizingPlayer: Boolean,
            alpha: Int,
            beta: Int,
            player: Player,
            currHash: Long
    ): Int {
        var alpha = alpha
        var beta = beta
        var bestValue: Int
        if (isMaximizingPlayer) {
            bestValue = Int.MIN_VALUE
            var possibleMoves = movesFinder.getMoves(board, player)

            possibleMoves = possibleMoves.reversed()
            possibleMoves = possibleMoves.take(10);

            for (currentMove in possibleMoves) {
                board.updateCellToPossibleValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
                val newHash = zobristHashing.updateHash(currHash, currentMove)
                var value: Int
                value = if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    evaluator.evaluate(board, currentMove)
                } else if (currDepth + 1 > maxDepth) {
                    evaluator.evaluate(board, currentMove, newHash)
                } else {
                    minimax(board, currDepth + 1, maxDepth, false, alpha, beta, getRival(player), newHash)
                }
                board.updateCellValue(currentMove.y, currentMove.x, CellType.EMPTY)
                bestValue = max(bestValue, value)
                alpha = max(alpha, bestValue)
                if (beta <= alpha) {
                    break
                }
            }
        } else {
            bestValue = Int.MAX_VALUE
            var possibleMoves = movesFinder.getMoves(board, player)
            possibleMoves = possibleMoves.reversed()
            possibleMoves = possibleMoves.take(10)

            for (currentMove in possibleMoves) {
                board.updateCellToPossibleValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
                val newHash = zobristHashing.updateHash(currHash, currentMove)
                var value: Int
                value = if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    evaluator.evaluate(board, currentMove)
                } else if (currDepth + 1 > maxDepth) {
                    evaluator.evaluate(board, currentMove, newHash)
                } else {
                    minimax(board, currDepth + 1, maxDepth, true, alpha, beta, getRival(player), newHash)
                }
                board.updateCellValue(currentMove.y, currentMove.x, CellType.EMPTY)
                bestValue = min(bestValue, value)
                beta = min(beta, bestValue)
                if (beta <= alpha) {
                    break
                }
            }
        }
        return bestValue
    }
}