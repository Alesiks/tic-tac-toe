package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.Constants;
import by.xxx.pupil.ai.minimax.Evaluator;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameState;

import static by.xxx.pupil.Constants.DRAW_STRATEGY_SCORE;

public class DefaultEvaluator implements Evaluator {

    @Override
    public int evaluate(Board board, GameState gameState) {
        return mapResultToScore(gameState);
    }

    private int mapResultToScore(GameState gameState) {
        if (GameState.NOUGHT_WIN == gameState) {
            return Constants.WIN_STRATEGY_SCORE;
        } else if (GameState.CROSS_WIN == gameState) {
            return Constants.LOSE_STRATEGY_SCORE;
        } else if (GameState.DRAW == gameState) {
            return DRAW_STRATEGY_SCORE;
        } else if (GameState.GAME_CONTINUES == gameState) {
            return DRAW_STRATEGY_SCORE;
        } else {
            throw new RuntimeException("Unknown state");
        }
    }

}
