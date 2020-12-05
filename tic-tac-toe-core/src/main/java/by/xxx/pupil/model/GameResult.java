package by.xxx.pupil.model;

import by.xxx.pupil.Constants;

import static by.xxx.pupil.Constants.DRAW_STRATEGY_SCORE;

public enum GameResult {

    CROSS_WIN,
    NOUGHT_WIN,
    GAME_CONTINUES,
    DRAW;

    public static int mapResultToScore(GameResult gameResult) {
        if (GameResult.NOUGHT_WIN == gameResult) {
            return Constants.WIN_STRATEGY_SCORE;
        } else if (GameResult.CROSS_WIN == gameResult) {
            return Constants.LOSE_STRATEGY_SCORE;
        } else if (GameResult.DRAW == gameResult) {
            return DRAW_STRATEGY_SCORE;
        } else {
            throw new RuntimeException("Game not in terminal state");
        }
    }

}
