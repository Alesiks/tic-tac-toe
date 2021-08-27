package by.xxx.pupil.ai.minimax

import by.xxx.pupil.Constants
import by.xxx.pupil.Constants.MINIMAX_DEPTH_PROPERTY
import by.xxx.pupil.ai.AIPlayer
import by.xxx.pupil.ai.hashing.ZobristHashing
import by.xxx.pupil.ai.minimax.findmoves.MovesFinder
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import by.xxx.pupil.model.getCorrespondingCellType
import by.xxx.pupil.model.getRival
import by.xxx.pupil.winning.WinnerFinder
import org.apache.logging.log4j.LogManager

class MinimaxBasedAI(
        private val movesFinder: MovesFinder,
        private val winnerFinder: WinnerFinder,
        private val minimax: Minimax,
        private val zobristHashing: ZobristHashing
) : AIPlayer {

    private val logger = LogManager.getLogger(MinimaxBasedAI::class)

    override fun nextMove(board: Board, player: Player, properties: Map<String, Any>): Move {
        val startTime = System.currentTimeMillis()
        logger.info("Start thinking on move for position:\n$board")

        val maxDepth: Int = properties[MINIMAX_DEPTH_PROPERTY] as Int

        val bestMoves: MutableList<Move> = ArrayList()
        var bestValue = Int.MIN_VALUE
        var possibleMoves = movesFinder.getMoves(board, player)

        possibleMoves= possibleMoves.reversed()
        possibleMoves = possibleMoves.take(10);

        val hash = zobristHashing.hash(board)
        for (currentMove in possibleMoves) {
            board.updateCellToPossibleValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
            var value: Int
            //                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            value = if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                board.updateCellValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
                val time = System.currentTimeMillis() - startTime
                logger.info("Make a move[$currentMove] in $time (ms), move lead to win!\nboard:\n$board")
                return currentMove
            } else {
                val newHash = zobristHashing.updateHash(hash, currentMove)
                minimax.minimax(board, 0, maxDepth, false, Constants.LOSE_STRATEGY_SCORE, Constants.WIN_STRATEGY_SCORE, getRival(player), newHash)
            }
            board.updateCellValue(currentMove.y, currentMove.x, CellType.EMPTY)
            if (value > bestValue) {
                bestMoves.clear()
                bestMoves.add(currentMove)
                bestValue = value
            } else if (value == bestValue) {
                bestMoves.add(currentMove)
            }
        }

        val move = bestMoves[(0 until bestMoves.size).random()]
        board.updateCellValue(move.y, move.x, getCorrespondingCellType(player))

        val time = System.currentTimeMillis() - startTime
        logger.info("Make a move[$move] in $time (ms) board:\n$board")

        return move
    }


}