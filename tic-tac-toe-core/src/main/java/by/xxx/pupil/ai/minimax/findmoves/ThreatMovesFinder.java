package by.xxx.pupil.ai.minimax.findmoves;

import by.xxx.pupil.CombinationsFinder;
import by.xxx.pupil.GeneralCombination;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

public class ThreatMovesFinder implements MovesFinder {

    private final CombinationsFinder combinationsFinder;
    private final InRadiusMovesFinder inRadiusMovesFinder;

    public ThreatMovesFinder(
            InRadiusMovesFinder inRadiusMovesFinder,
            CombinationsFinder combinationsFinder
    ) {
        Validate.notNull(combinationsFinder, "combinationsFinder is null");
        Validate.notNull(inRadiusMovesFinder, "defaultMovesFinder is null");

        this.combinationsFinder = combinationsFinder;
        this.inRadiusMovesFinder = inRadiusMovesFinder;
    }

    @Override
    public List<Move> getMoves(Board board, Player player) {
        List<Move> moves = inRadiusMovesFinder.getMoves(board, player);

        List<Move> threatSpaceMoves = new ArrayList<>();

        for (Move move : moves) {
            board.updateCellValue(move.getI(), move.getJ(), Player.CROSSES.equals(player) ? CellType.CROSS : CellType.NOUGHT);
            List<GeneralCombination> patternsList = combinationsFinder.getCombinations(board, move);
            if (
                    patternsList.contains(GeneralCombination.STRAIGHT_FOUR) ||
                            patternsList.contains(GeneralCombination.FOUR) ||
                            patternsList.contains(GeneralCombination.THREE) ||
                            patternsList.contains(GeneralCombination.BROKEN_THREE)
            ) {
                threatSpaceMoves.add(move);
            }
            board.updateCellValue(move.getI(), move.getJ(), CellType.EMPTY);
        }

        return !threatSpaceMoves.isEmpty() ? threatSpaceMoves : moves;
    }
}
