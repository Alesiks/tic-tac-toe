package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameState;

public interface Evaluator {

    int evaluate(Board board, GameState gameState);
}
