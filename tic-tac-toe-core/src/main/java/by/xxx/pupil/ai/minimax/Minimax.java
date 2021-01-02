package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameResult;
import by.xxx.pupil.model.Move;

import java.util.List;

import static by.xxx.pupil.Constants.DRAW_STRATEGY_SCORE;


public class Minimax {

    private final int depthLimit;
    private final WinnerFinder winnerFinder = new WinnerFinder();
    private final PossibleMovesFinder possibleMovesFinder;

    public Minimax(int depthLimit, PossibleMovesFinder possibleMovesFinder) {
        this.depthLimit = depthLimit;
        this.possibleMovesFinder = possibleMovesFinder;
    }

    /**
     * @param board
     * @param currDepth
     * @param isMaximizingPlayer
     * @param alpha              is the best value that the maximizer currently can guarantee at that level or above.
     * @param beta               is the best value that the minimizer currently can guarantee at that level or above.
     * @return
     */
    public int minimax(Board board, int currDepth, boolean isMaximizingPlayer, int alpha, int beta) {
//        GameResult gameResult = winnerFinder.gameResult(board);
//        if (isBoardInTerminalState(gameResult)) {
//            return GameResult.mapResultToScore(gameResult);
//        }

        if (currDepth > depthLimit) {
            return DRAW_STRATEGY_SCORE;
        }

        int bestValue;
        if (isMaximizingPlayer) {
            bestValue = Integer.MIN_VALUE;
            List<Move> possibleMoves = possibleMovesFinder.getAvailableMoves(board);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), CellType.NOUGHT);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    GameResult gameResult = GameResult.NOUGHT_WIN;
                    value = GameResult.mapResultToScore(gameResult);
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
            List<Move> possibleMoves = possibleMovesFinder.getAvailableMoves(board);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), CellType.CROSS);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    GameResult gameResult = GameResult.CROSS_WIN;
                    value = GameResult.mapResultToScore(gameResult);
                } else {
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
