package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;

public interface Evaluator {

    int evaluate(Board board, GameState gameState, Move lastMove);
}
