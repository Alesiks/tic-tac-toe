package by.xxx.pupil.ai.minimax.evaluate;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.minimax.evaluate.Evaluator;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.apache.commons.lang3.Validate;

public class DefaultEvaluator implements Evaluator {

    private static final int AI_WIN_SCORE = 100;
    private static final int DRAW_SCORE = 0;
    private static final int PERSON_WIN_SCORE = -100;

    private final WinnerFinder winnerFinder;

    public DefaultEvaluator(WinnerFinder winnerFinder) {
        Validate.notNull(winnerFinder, "winnerFinder is null");

        this.winnerFinder = winnerFinder;
    }

    @Override
    public int evaluate(Board board, Move lastMove) {
        if (winnerFinder.isMoveLeadToWin(board, lastMove)) {
            if (Player.CROSSES.equals(lastMove.getPlayer())) {
                return PERSON_WIN_SCORE;
            } else {
                return AI_WIN_SCORE;
            }
        }

        return DRAW_SCORE;
    }

    @Override
    public int evaluate(Board board, Move lastMove, long hash) {
        return evaluate(board, lastMove);
    }

}
