package by.xxx.pupil

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

class CombinationsFinder(private val combinationPatterns: CombinationPatterns) {

    fun findNoughtsCombinations(line: String): MutableList<GeneralCombination>? {
        return combinationPatterns.noughtsPatternsToCombinations.keys
                .stream()
                .filter { pattern: Pattern -> pattern.matcher(line).find() }
                .map { key: Pattern -> combinationPatterns.noughtsPatternsToCombinations[key] }
                .collect(Collectors.toList())
    }

    fun findCrossesCombinations(line: String): MutableList<GeneralCombination>? {
        return combinationPatterns.crossesPatternsToCombinations.keys.stream()
                .filter { pattern: Pattern -> pattern.matcher(line).find() }
                .map { key: Pattern -> combinationPatterns.crossesPatternsToCombinations[key] }
                .collect(Collectors.toList())
    }

    fun getCombinations(board: Board, move: Move): List<GeneralCombination> {
        val lines = getPossibleLinesForCombinations(board, move)
        val resultCombinations: MutableList<GeneralCombination> = ArrayList()
        for (line in lines) {
            if (Player.CROSSES == move.player) {
                when {
                    CombinationPatterns.STRAIGHT_FOUR_CROSSES_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.STRAIGHT_FOUR)
                    }
                    CombinationPatterns.FOUR_CROSSES_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.FOUR)
                    }
                    CombinationPatterns.THREE_CROSSES_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.THREE)
                    }
                    CombinationPatterns.BROKEN_THREE_CROSSES_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.BROKEN_THREE)
                    }
                }
            } else {
                when {
                    CombinationPatterns.STRAIGHT_FOUR_NOUGHTS_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.STRAIGHT_FOUR)
                    }
                    CombinationPatterns.FOUR_NOUGHTS_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.FOUR)
                    }
                    CombinationPatterns.THREE_NOUGHTS_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.THREE)
                    }
                    CombinationPatterns.BROKEN_THREE_NOUGHTS_PATTERN.matcher(line).find() -> {
                        resultCombinations.add(GeneralCombination.BROKEN_THREE)
                    }
                }
            }
        }
        return resultCombinations
    }

    private fun getPossibleLinesForCombinations(board: Board, move: Move): List<String> {
        return Stream.of(
                board.getHorizontalLine(move.i),
                board.getVerticalLine(move.j),
                board.getLeftToRightDiagonalLine(move.i, move.j),
                board.getRightToLeftDiagonalLine(move.i, move.j)
        ).collect(Collectors.toList())
    }
}