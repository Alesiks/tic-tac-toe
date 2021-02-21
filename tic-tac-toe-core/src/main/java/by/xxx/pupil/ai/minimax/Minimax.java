package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;

import java.util.List;


public class Minimax {

    private final int depthLimit;
    private final WinnerFinder winnerFinder;
    private final MovesFinder movesFinder;
    private final Evaluator evaluator;

    public Minimax(int depthLimit, MovesFinder movesFinder, Evaluator evaluator, WinnerFinder winnerFinder) {
        this.depthLimit = depthLimit;
        this.movesFinder = movesFinder;
        this.evaluator = evaluator;
        this.winnerFinder = winnerFinder;
    }

    /**
     * @param board - presents current game state
     * @param currDepth - current level in game tree which is build using minimax algorithm
     * @param isMaximizingPlayer - is current player maximize score or minimize
     * @param alpha - the best value that the maximizer currently can guarantee at that level or above.
     * @param beta - the best value that the minimizer currently can guarantee at that level or above.
     * @return min or max (depends on player type) value that minimax can achieve on that level
     */
    public int minimax(Board board, int currDepth, boolean isMaximizingPlayer, int alpha, int beta) {
        int bestValue;
        if (isMaximizingPlayer) {
            bestValue = Integer.MIN_VALUE;
            List<Move> possibleMoves = movesFinder.getMoves(board, false);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), CellType.NOUGHT);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    value = evaluator.evaluate(board, currentMove);
                } else if(currDepth + 1 > depthLimit) {
                    value = evaluator.evaluate(board, currentMove);
                } else {
                    value = minimax(board, currDepth + 1, false, alpha, beta);
                }
                board.updateCellValue(currentMove.getI(), currentMove.getJ(), CellType.EMPTY);
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(alpha, bestValue);
                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            List<Move> possibleMoves = movesFinder.getMoves(board, true);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), CellType.CROSS);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    value = evaluator.evaluate(board, currentMove);
                } else if(currDepth + 1 > depthLimit) {
                    value = evaluator.evaluate(board, currentMove);
                }else {
                    value = minimax(board, currDepth + 1, true, alpha, beta);
                }
                board.updateCellValue(currentMove.getI(), currentMove.getJ(), CellType.EMPTY);
                bestValue = Math.min(bestValue, value);
                beta = Math.min(beta, bestValue);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        return bestValue;
    }

}
