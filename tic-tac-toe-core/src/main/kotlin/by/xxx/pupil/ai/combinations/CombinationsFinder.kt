package by.xxx.pupil.ai.combinations

import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.BROKEN_THREE_CROSSES_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.BROKEN_THREE_NOUGHTS_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.FOUR_CROSSES_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.FOUR_NOUGHTS_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.STRAIGHT_FOUR_CROSSES_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.STRAIGHT_FOUR_NOUGHTS_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.THREE_CROSSES_PATTERN
import by.xxx.pupil.ai.combinations.CombinationPatterns.Companion.THREE_NOUGHTS_PATTERN
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

class CombinationsFinder(private val combinationPatterns: CombinationPatterns) {

    fun findNoughtsCombinations(line: String): List<GeneralCombination?> {
        return combinationPatterns.noughtsPatternsToCombinations.keys
                .asSequence()
                .filter { pattern: Regex -> line.contains(pattern)}
                .map { key: Regex -> combinationPatterns.noughtsPatternsToCombinations[key] }
                .toList();
    }

    fun findCrossesCombinations(line: String): List<GeneralCombination?> {
        return combinationPatterns.crossesPatternsToCombinations.keys
                .asSequence()
                .filter { pattern: Regex -> line.contains(pattern) }
                .map { key: Regex -> combinationPatterns.crossesPatternsToCombinations[key] }
                .toList();
    }

    fun getCombinations(board: Board, move: Move): List<GeneralCombination> {
        val lines = getPossibleLinesForCombinations(board, move)
        val resultCombinations: MutableList<GeneralCombination> = ArrayList()
        for (line in lines) {
            if (Player.CROSSES == move.player) {
                when {
                    line.contains(STRAIGHT_FOUR_CROSSES_PATTERN) -> resultCombinations.add(GeneralCombination.STRAIGHT_FOUR)
                    line.contains(FOUR_CROSSES_PATTERN) -> resultCombinations.add(GeneralCombination.FOUR)
                    line.contains(THREE_CROSSES_PATTERN) -> resultCombinations.add(GeneralCombination.THREE)
                    line.contains(BROKEN_THREE_CROSSES_PATTERN) -> resultCombinations.add(GeneralCombination.BROKEN_THREE)
                }
            } else {
                when {
                    line.contains(STRAIGHT_FOUR_NOUGHTS_PATTERN) -> resultCombinations.add(GeneralCombination.STRAIGHT_FOUR)
                    line.contains(FOUR_NOUGHTS_PATTERN) -> resultCombinations.add(GeneralCombination.FOUR)
                    line.contains(THREE_NOUGHTS_PATTERN) -> resultCombinations.add(GeneralCombination.THREE)
                    line.contains(BROKEN_THREE_NOUGHTS_PATTERN) -> resultCombinations.add(GeneralCombination.BROKEN_THREE)
                }
            }
        }
        return resultCombinations
    }

    private fun getPossibleLinesForCombinations(board: Board, move: Move): List<String> {
        return listOf(
                board.getHorizontalLine(move.y),
                board.getVerticalLine(move.x),
                board.getLeftToRightDiagonalLine(move.y, move.x),
                board.getRightToLeftDiagonalLine(move.y, move.x)
        );
    }
}