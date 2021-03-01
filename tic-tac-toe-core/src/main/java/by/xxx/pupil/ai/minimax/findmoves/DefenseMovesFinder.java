package by.xxx.pupil.ai.minimax.findmoves;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.apache.commons.lang3.Validate;

import java.util.List;

public class DefenseMovesFinder implements MovesFinder {

    private final InRadiusMovesFinder inRadiusMovesFinder;

    public DefenseMovesFinder(InRadiusMovesFinder inRadiusMovesFinder) {
        Validate.notNull(inRadiusMovesFinder, "null inRadiusMovesFinder");

        this.inRadiusMovesFinder = inRadiusMovesFinder;
    }

    @Override
    public List<Move> getMoves(Board board, Player player) {
        List<Move> moves = inRadiusMovesFinder.getMoves(board, player);


        return null;
    }

}
