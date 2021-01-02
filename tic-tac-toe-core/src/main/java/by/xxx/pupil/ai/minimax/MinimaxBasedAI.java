package by.xxx.pupil.ai.minimax;


import by.xxx.pupil.Constants;
import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameResult;
import by.xxx.pupil.model.Move;

import java.util.List;

import static by.xxx.pupil.Constants.DEFAULT_MINIMAX_DEPTH_LIMIT;

public class MinimaxBasedAI implements AIPlayer {

    private final Minimax minimax = new Minimax(DEFAULT_MINIMAX_DEPTH_LIMIT, new DefaultMovesFinder());
    private final WinnerFinder winnerFinder = new WinnerFinder();
    private final PossibleMovesFinder possibleMovesFinder = new DefaultMovesFinder();

    @Override
    public Move nextMove(Board board, CellType aiCellType) {
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        List<Move> possibleMoves = possibleMovesFinder.getAvailableMoves(board);
        for (Move currentMove : possibleMoves) {
            board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), aiCellType);
            int value;
//                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                GameResult gameResult = GameResult.NOUGHT_WIN;
                value = GameResult.mapResultToScore(gameResult);
            } else {
                value = minimax.minimax(board, 0, false, Constants.LOSE_STRATEGY_SCORE, Constants.WIN_STRATEGY_SCORE);
            }

            board.updateCellValue(currentMove.getI(), currentMove.getJ(), CellType.EMPTY);
            if (value > bestValue) {
                bestMove = currentMove;
                bestValue = value;
            }


        }

        return bestMove;
    }

}
