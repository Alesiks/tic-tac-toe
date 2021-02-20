package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CombinationsFinder {

    private CombinationPatterns patterns;

    public List<GeneralCombinationNames> findAICombination(String line) {
        return CombinationPatterns.aiPatterns.stream()
                .filter(pattern -> pattern.matcher(line).find())
                .map(CombinationPatterns.aiPatternsToCombinations::get)
                .collect(Collectors.toList());
    }

    public List<GeneralCombinationNames> findPersonCombination(String line) {
        return CombinationPatterns.personPatterns.stream()
                .filter(pattern -> pattern.matcher(line).find())
                .map(CombinationPatterns.personPatternsToCombinations::get)
                .collect(Collectors.toList());
    }

    public List<GeneralCombinationNames> getCombinations(Board board, Move move) {
        List<String> lines = getPossibleLinesForCombinations(board, move);

        List<GeneralCombinationNames> resultCombinations = new ArrayList<>();
        for (String line : lines) {
            if(move.isPerson()) {
                if (CombinationPatterns.straightFourPatternPerson.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.STRAIGHT_FOUR);
                } else if (CombinationPatterns.fourPatternPerson.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.FOUR);
                } else if (CombinationPatterns.threePatternPerson.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.THREE);
                } else if (CombinationPatterns.brokenThreePatternPerson.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.BROKEN_THREE);
                }
            } else {
                if (CombinationPatterns.straightFourPatternAI.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.STRAIGHT_FOUR);
                } else if (CombinationPatterns.fourPatternAI.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.FOUR);
                } else if (CombinationPatterns.threePatternAI.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.THREE);
                } else if (CombinationPatterns.brokenThreePatternAI.matcher(line).find()) {
                    resultCombinations.add(GeneralCombinationNames.BROKEN_THREE);
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
