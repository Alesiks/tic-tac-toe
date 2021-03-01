package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.hashing.ZobristHashing;
import by.xxx.pupil.ai.minimax.evaluate.Evaluator;
import by.xxx.pupil.ai.minimax.findmoves.MovesFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;

import java.util.Collections;
import java.util.List;

import static by.xxx.pupil.model.PlayerUtils.getCorrespondingCellType;
import static by.xxx.pupil.model.PlayerUtils.getRival;


public class Minimax {

    private final int depthLimit;
    private final WinnerFinder winnerFinder;
    private final MovesFinder movesFinder;
    private final Evaluator evaluator;
    private final ZobristHashing zobristHashing;

    public Minimax(int depthLimit, MovesFinder movesFinder, Evaluator evaluator, WinnerFinder winnerFinder, ZobristHashing zobristHashing) {
        this.depthLimit = depthLimit;
        this.movesFinder = movesFinder;
        this.evaluator = evaluator;
        this.winnerFinder = winnerFinder;
        this.zobristHashing = zobristHashing;
    }

    /**
     * @param board              - presents current game state
     * @param currDepth          - current level in game tree which is build using minimax algorithm
     * @param isMaximizingPlayer - is current player maximize score or minimize
     * @param alpha              - the best value that the maximizer currently can guarantee at that level or above.
     * @param beta               - the best value that the minimizer currently can guarantee at that level or above.
     * @param player             - current player
     * @param currHash           - Zobrist hash for current board state
     * @return min or max (depends on player type) value that minimax can achieve on that level
     */
    public int minimax(Board board, int currDepth, boolean isMaximizingPlayer, int alpha, int beta, Player player, long currHash) {
        int bestValue;
        if (isMaximizingPlayer) {
            bestValue = Integer.MIN_VALUE;
            List<Move> possibleMoves = movesFinder.getMoves(board, player);
            Collections.reverse(possibleMoves);

            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), getCorrespondingCellType(player));
                long newHash = zobristHashing.updateHash(currHash, currentMove);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    value = evaluator.evaluate(board, currentMove);
                } else if (currDepth + 1 > depthLimit) {
                    value = evaluator.evaluate(board, currentMove, newHash);
                } else {
                    value = minimax(board, currDepth + 1, false, alpha, beta, getRival(player), newHash);
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
            List<Move> possibleMoves = movesFinder.getMoves(board, player);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), getCorrespondingCellType(player));
                long newHash = zobristHashing.updateHash(currHash, currentMove);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    value = evaluator.evaluate(board, currentMove);
                } else if (currDepth + 1 > depthLimit) {
                    value = evaluator.evaluate(board, currentMove, newHash);
                } else {
                    value = minimax(board, currDepth + 1, true, alpha, beta, getRival(player), newHash);
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
