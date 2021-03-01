package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CombinationsFinder {

    private CombinationPatterns combinationPatterns;

    public CombinationsFinder(CombinationPatterns combinationPatterns) {
        this.combinationPatterns = combinationPatterns;
    }

    public List<GeneralCombination> findNoughtsCombinations(String line) {
        return combinationPatterns.getNoughtsPatternsToCombinations().keySet()
                .stream()
                .filter(pattern -> pattern.matcher(line).find())
                .map(combinationPatterns.getNoughtsPatternsToCombinations()::get)
                .collect(Collectors.toList());
    }

    public List<GeneralCombination> findCrossesCombinations(String line) {
        return combinationPatterns.getCrossesPatternsToCombinations().keySet().stream()
                .filter(pattern -> pattern.matcher(line).find())
                .map(combinationPatterns.getCrossesPatternsToCombinations()::get)
                .collect(Collectors.toList());
    }

    public List<GeneralCombination> getCombinations(Board board, Move move) {
        List<String> lines = getPossibleLinesForCombinations(board, move);

        List<GeneralCombination> resultCombinations = new ArrayList<>();
        for (String line : lines) {
            if(Player.CROSSES.equals(move.getPlayer())) {
                if (CombinationPatterns.STRAIGHT_FOUR_CROSSES_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.STRAIGHT_FOUR);
                } else if (CombinationPatterns.FOUR_CROSSES_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.FOUR);
                } else if (CombinationPatterns.THREE_CROSSES_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.THREE);
                } else if (CombinationPatterns.BROKEN_THREE_CROSSES_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.BROKEN_THREE);
                }
            } else {
                if (CombinationPatterns.STRAIGHT_FOUR_NOUGHTS_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.STRAIGHT_FOUR);
                } else if (CombinationPatterns.FOUR_NOUGHTS_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.FOUR);
                } else if (CombinationPatterns.THREE_NOUGHTS_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.THREE);
                } else if (CombinationPatterns.BROKEN_THREE_NOUGHTS_PATTERN.matcher(line).find()) {
                    resultCombinations.add(GeneralCombination.BROKEN_THREE);
                }
            }
        }

        return resultCombinations;
    }

    private List<String> getPossibleLinesForCombinations(Board board, Move move) {
        return Stream.of(
                board.getHorizontalLine(move.getI()),
                board.getVerticalLine(move.getJ()),
                board.getLeftToRightDiagonalLine(move.getI(), move.getJ()),
                board.getRightToLeftDiagonalLine(move.getI(), move.getJ())
        ).collect(toList());
    }

}
