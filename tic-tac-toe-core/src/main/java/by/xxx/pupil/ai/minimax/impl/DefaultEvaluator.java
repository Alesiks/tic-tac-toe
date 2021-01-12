package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.ai.minimax.Evaluator;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;

public class DefaultEvaluator implements Evaluator {

    private static final int AI_WIN_SCORE = 100;
    private static final int DRAW_SCORE = 0;
    private static final int PERSON_WIN_SCORE = -100;

    @Override
    public int evaluate(Board board, GameState gameState, Move lastMove) {
        return mapResultToScore(gameState);
    }

    private int mapResultToScore(GameState gameState) {
        if (GameState.NOUGHT_WIN == gameState) {
            return AI_WIN_SCORE;
        } else if (GameState.CROSS_WIN == gameState) {
            return PERSON_WIN_SCORE;
        } else if (GameState.DRAW == gameState || GameState.GAME_CONTINUES == gameState) {
            return DRAW_SCORE;
        } else {
            throw new RuntimeException("Unknown state");
        }
    }

}
