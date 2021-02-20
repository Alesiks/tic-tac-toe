package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.BoardUtils;
import by.xxx.pupil.ai.minimax.MovesFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;

import java.util.ArrayList;
import java.util.List;

public class AllAvailableMovesFinder implements MovesFinder {

    @Override
    public List<Move> getMoves(Board board, boolean isPerson) {
        List<Move> availableMoves = new ArrayList<>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (BoardUtils.isCellEmpty(board, i, j)) {
                    availableMoves.add(new Move(i, j, isPerson));
                }
            }
        }

        return availableMoves;
    }
}
