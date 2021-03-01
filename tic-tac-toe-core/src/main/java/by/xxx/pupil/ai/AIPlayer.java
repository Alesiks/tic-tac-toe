package by.xxx.pupil.ai;


import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;

import static by.xxx.pupil.Constants.DEFAULT_AI_TYPE;

public interface AIPlayer {

    Move nextMove(Board board, Player player);

    default Move nextMove(Board board) {
        return nextMove(board, DEFAULT_AI_TYPE);
    }

}
