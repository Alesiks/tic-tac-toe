package by.xxx.pupil.ai.minimax.evaluate;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;

public interface Evaluator {

    int evaluate(Board board, Move lastMove);

    int evaluate(Board board, Move lastMove, long hash);
}
