package by.xxx.pupil.ai.minimax;


import by.xxx.pupil.Board;
import by.xxx.pupil.CellType;
import by.xxx.pupil.Constants;
import by.xxx.pupil.ai.Move;
import by.xxx.pupil.ai.NextMoveFinder;

import static by.xxx.pupil.BoardUtils.isCellValueEmpty;
import static by.xxx.pupil.Constants.DEFAULT_MINIMAX_DEPTH_LIMIT;

public class MinimaxAlgorithmBasedAI implements NextMoveFinder {

    private final Minimax minimax = new Minimax(DEFAULT_MINIMAX_DEPTH_LIMIT);

    @Override
    public Move findNextMove(Board board, CellType aiCellType) {
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (isCellValueEmpty(board, i, j)) {
                    Move currentMove = new Move(i, j);
                    board.updateCellToPossibleValue(i, j, aiCellType);
//                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    int value = minimax.minimax(board, 0, false, Constants.LOSE_STRATEGY_SCORE, Constants.WIN_STRATEGY_SCORE);
                    board.updateCellValue(i, j, CellType.EMPTY);
                    if(value > bestValue) {
                        bestMove = currentMove;
                        bestValue = value;
                    }

                }
            }
        }

        return bestMove;
    }

}
