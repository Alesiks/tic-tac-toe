package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.ai.minimax.Evaluator;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;

public class TrickyEvaluator implements Evaluator {

    private static final int AI_WIN_SCORE = 100;
    private static final int PERSON_WIN_SCORE = -100;
    private static final int DRAW_SCORE = 0;

    private static final int AI_FOUR_OPEN_IN_A_ROW = 95;  // *0000*
    private static final int AI_THREE_OPEN_IN_A_ROW = 80; // *000*
    private static final int AI_FOUR_PARTLY_OPEN_IN_A_ROW = 80; // *x000* or *000x*

    private static final int PERSON_FOUR_OPEN_IN_A_ROW = 95;  // *xxxx*
    private static final int PERSON_THREE_OPEN_IN_A_ROW = 80; // *xxx*
    private static final int PERSON_FOUR_PARTLY_OPEN_IN_A_ROW = 80; // *oxxx* or *xxx0*


    @Override
    public int evaluate(Board board, GameState gameState, Move lastMove) {
        if(gameState == GameState.NOUGHT_WIN) {
            return AI_WIN_SCORE;
        } else if(gameState == GameState.CROSS_WIN) {
            return PERSON_WIN_SCORE;
        } else {



        }

        return DRAW_SCORE;
    }

}
