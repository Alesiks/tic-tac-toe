package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.*;

import static by.xxx.pupil.BoardUtils.isCellValueEmpty;
import static by.xxx.pupil.Constants.DRAW_STRATEGY_SCORE;


public class Minimax {

    private final int depthLimit;
    private final GameStrategy gameStrategy = new GameStrategy();

    public Minimax(int depthLimit) {
        this.depthLimit = depthLimit;
    }

    /**
     *
     * @param board
     * @param currDepth
     * @param isMaximizingPlayer
     * @param alpha  is the best value that the maximizer currently can guarantee at that level or above.
     * @param beta is the best value that the minimizer currently can guarantee at that level or above.
     * @return
     */
    public int minimax(Board board, int currDepth, boolean isMaximizingPlayer, int alpha, int beta) {
        GameResult gameResult = gameStrategy.gameResult(board);
        if (isBoardInTerminalState(gameResult)) {
            return mapResultToScore(gameResult);
        }

        if (currDepth > depthLimit) {
            return DRAW_STRATEGY_SCORE;
        }



        int bestValue;
        if (isMaximizingPlayer) {
            boolean stopFinding = false;
            bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (isCellValueEmpty(board, i, j)) {
                        board.updateCellToPossibleValue(i, j, Cell.NOUGHT);
                        int value = minimax(board, currDepth + 1, false, alpha, beta);
                        board.updateCellValue(i, j, Cell.EMPTY);
                        bestValue = Math.max(bestValue, value);
                        alpha = Math.max(alpha, bestValue);
                        if(beta <= alpha) {
                            stopFinding = true;
                            break;
                        }
                    }
                }
                if(stopFinding) {
                    break;
                }
            }
        } else {
            boolean stopFinding = false;
            bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (isCellValueEmpty(board, i, j)) {
                        board.updateCellToPossibleValue(i, j, Cell.CROSS);
                        int value = minimax(board, currDepth + 1, true, alpha, beta);
                        board.updateCellValue(i, j, Cell.EMPTY);
                        bestValue = Math.min(bestValue, value);
                        beta = Math.min(beta, bestValue);
                        if(beta <= alpha) {
                            stopFinding = true;
                            break;
                        }
                    }
                }
                if(stopFinding) {
                    break;
                }
            }
        }

        return bestValue;
    }

    public boolean isBoardInTerminalState(GameResult gameResult) {
        return GameResult.GAME_CONTINUES != gameResult;
    }

    public int mapResultToScore(GameResult gameResult) {
        if (GameResult.NOUGHT_WIN == gameResult) {
            return Constants.WIN_STRATEGY_SCORE;
        } else if (GameResult.CROSS_WIN == gameResult) {
            return Constants.LOSE_STRATEGY_SCORE;
        } else if (GameResult.DRAW == gameResult) {
            return DRAW_STRATEGY_SCORE;
        } else {
            throw new RuntimeException("Game not in terminal state");
        }
    }


}
