package by.pupil.ai.minimax

import by.pupil.Constants
import by.pupil.Constants.MINIMAX_DEPTH_PROPERTY
import by.pupil.ai.AIPlayer
import by.pupil.ai.minimax.findmoves.MovesFinder
import by.pupil.model.Board
import by.pupil.model.CellType
import by.pupil.model.Move
import by.pupil.model.Player
import by.pupil.model.getCorrespondingCellType
import by.pupil.model.getRival
import by.pupil.winning.WinnerFinder
import mu.KotlinLogging

class MinimaxBasedAI(
    private val movesFinder: MovesFinder,
    private val winnerFinder: WinnerFinder,
    private val minimax: Minimax,
) : AIPlayer {
    val logger = KotlinLogging.logger {}

    override fun nextMove(
        board: Board,
        player: Player,
        properties: Map<String, Any>,
    ): Move {
        val startTime = System.currentTimeMillis()
        logger.info("Start thinking on move for position:\n$board")

        val maxDepth: Int = properties[MINIMAX_DEPTH_PROPERTY] as Int

        val bestMoves: MutableList<Move> = ArrayList()
        var bestValue = Int.MIN_VALUE
        val possibleMoves = movesFinder.getMoves(board, player)

        for (currentMove in possibleMoves) {
            board.updateCellToPossibleValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
            var value: Int
            //                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            value =
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    board.updateCellValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
                    val time = System.currentTimeMillis() - startTime
                    logger.info("Make a move[$currentMove] in $time (ms), move lead to win!\nboard:\n$board")
                    return currentMove
                } else {
                    minimax.minimax(
                        board,
                        0,
                        maxDepth,
                        false,
                        Constants.LOSE_STRATEGY_SCORE,
                        Constants.WIN_STRATEGY_SCORE,
                        getRival(player),
                    )
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
