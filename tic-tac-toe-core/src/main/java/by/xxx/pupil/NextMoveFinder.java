package by.xxx.pupil;


import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;

import static by.xxx.pupil.Constants.DEFAULT_AI_CELL_TYPE_TYPE;

public interface NextMoveFinder {

    Move findNextMove(Board board, CellType cellType);

    default Move findNextMove(Board board) {
        return findNextMove(board, DEFAULT_AI_CELL_TYPE_TYPE);
    }

}
