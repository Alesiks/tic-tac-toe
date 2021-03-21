package by.xxx.pupil.ai.minimax

import by.xxx.pupil.Constants
import by.xxx.pupil.WinnerFinder
import by.xxx.pupil.ai.AIPlayer
import by.xxx.pupil.ai.hashing.ZobristHashing
import by.xxx.pupil.ai.minimax.evaluate.Evaluator
import by.xxx.pupil.ai.minimax.findmoves.MovesFinder
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import by.xxx.pupil.model.PlayerUtils.getCorrespondingCellType
import by.xxx.pupil.model.PlayerUtils.getRival
import org.apache.logging.log4j.LogManager
import java.util.*

class MinimaxBasedAI(
        private val movesFinder: MovesFinder,
        private val winnerFinder: WinnerFinder,
        private val minimax: Minimax,
        private val zobristHashing: ZobristHashing
) : AIPlayer {

    private val logger = LogManager.getLogger(MinimaxBasedAI::class.java)

    override fun nextMove(board: Board, player: Player, maxDepth: Int): Move {
        val bestMoves: MutableList<Move> = ArrayList()
        var bestValue = Int.MIN_VALUE
        var possibleMoves = movesFinder.getMoves(board, player)

        Collections.reverse(possibleMoves)
        possibleMoves = possibleMoves.take(10);

        val hash = zobristHashing.hash(board)
        for (currentMove in possibleMoves) {
            board.updateCellToPossibleValue(currentMove.i, currentMove.j, getCorrespondingCellType(player))
            var value: Int
            //                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            value = if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                return currentMove
            } else {
                val newHash = zobristHashing.updateHash(hash, currentMove)
                minimax.minimax(board, 0, maxDepth, false, Constants.LOSE_STRATEGY_SCORE, Constants.WIN_STRATEGY_SCORE, getRival(player), newHash)
                //                logger.debug("score for move[{},{}]: [{}]", currentMove.getI(), currentMove.getJ(), value);
            }
            board.updateCellValue(currentMove.i, currentMove.j, CellType.EMPTY)
            if (value > bestValue) {
                bestMoves.clear()
                bestMoves.add(currentMove)
                bestValue = value
            } else if (value == bestValue) {
                bestMoves.add(currentMove)
            }
        }
        return bestMoves[Random().nextInt(bestMoves.size)]
    }

}