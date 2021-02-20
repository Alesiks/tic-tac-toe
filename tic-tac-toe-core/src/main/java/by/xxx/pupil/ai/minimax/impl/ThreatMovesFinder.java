package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.CombinationPatterns;
import by.xxx.pupil.CombinationsFinder;
import by.xxx.pupil.GeneralCombinationNames;
import by.xxx.pupil.ai.minimax.MovesFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;
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
    public List<Move> getMoves(Board board, boolean isPerson) {
        List<Move> moves = inRadiusMovesFinder.getMoves(board, isPerson);

        List<Move> threatSpaceMoves = new ArrayList<>();

        for (Move move : moves) {
            board.updateCellValue(move.getI(), move.getJ(), move.isPerson() ? CellType.CROSS : CellType.NOUGHT);
            List<GeneralCombinationNames> patternsList = combinationsFinder.getCombinations(board, move);
            if (
                    patternsList.contains(GeneralCombinationNames.STRAIGHT_FOUR) ||
                            patternsList.contains(GeneralCombinationNames.FOUR) ||
                            patternsList.contains(GeneralCombinationNames.THREE) ||
                            patternsList.contains(GeneralCombinationNames.BROKEN_THREE)
            ) {
                threatSpaceMoves.add(move);
            }
            board.updateCellValue(move.getI(), move.getJ(), CellType.EMPTY);
        }

        return !threatSpaceMoves.isEmpty() ? threatSpaceMoves : moves;
    }
}
