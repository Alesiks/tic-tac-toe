package by.xxx.pupil.ai.minimax;


import by.xxx.pupil.Constants;
import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;

import java.util.List;

public class MinimaxBasedAI implements AIPlayer {

    private final MovesFinder movesFinder;
    private final WinnerFinder winnerFinder;
    private final Evaluator evaluator;
    private final Minimax minimax;

    public MinimaxBasedAI(
            MovesFinder movesFinder,
            Evaluator evaluator,
            WinnerFinder winnerFinder,
            Minimax minimax
    ) {
        this.movesFinder = movesFinder;
        this.evaluator = evaluator;
        this.winnerFinder = winnerFinder;
        this.minimax = minimax;
    }

    @Override
    public Move nextMove(Board board, CellType aiCellType) {
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        List<Move> possibleMoves = movesFinder.getMoves(board, false);
        for (Move currentMove : possibleMoves) {
            board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), aiCellType);
            int value;
//                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                value = evaluator.evaluate(board, GameState.NOUGHT_WIN, currentMove);
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
