package by.xxx.pupil.ai;


import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;

import static by.xxx.pupil.Constants.DEFAULT_AI_CELL_TYPE_TYPE;

public interface AIPlayer {

    Move nextMove(Board board, CellType cellType);

    default Move nextMove(Board board) {
        return nextMove(board, DEFAULT_AI_CELL_TYPE_TYPE);
    }

}
