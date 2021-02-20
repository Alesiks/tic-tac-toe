package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.ai.minimax.MovesFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import org.apache.commons.lang3.Validate;

import java.util.List;

public class DefenseMovesFinder implements MovesFinder {

    private final InRadiusMovesFinder inRadiusMovesFinder;

    public DefenseMovesFinder(InRadiusMovesFinder inRadiusMovesFinder) {
        Validate.notNull(inRadiusMovesFinder, "null inRadiusMovesFinder");

        this.inRadiusMovesFinder = inRadiusMovesFinder;
    }

    @Override
    public List<Move> getMoves(Board board, boolean isPerson) {
        List<Move> moves = inRadiusMovesFinder.getMoves(board, isPerson);


        return null;
    }

}
