package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;

import java.util.List;

public interface PossibleMovesFinder {

    List<Move> getAvailableMoves(Board board);

}
