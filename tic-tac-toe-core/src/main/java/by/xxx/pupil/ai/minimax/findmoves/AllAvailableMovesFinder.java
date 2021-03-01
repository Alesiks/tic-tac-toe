package by.xxx.pupil.ai.minimax.findmoves;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;

import java.util.ArrayList;
import java.util.List;

import static by.xxx.pupil.BoardUtils.isCellEmpty;

public class AllAvailableMovesFinder implements MovesFinder {

    @Override
    public List<Move> getMoves(Board board, Player player) {
        List<Move> availableMoves = new ArrayList<>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (isCellEmpty(board, i, j)) {
                    availableMoves.add(new Move(i, j, player));
                }
            }
        }

        return availableMoves;
    }
}
