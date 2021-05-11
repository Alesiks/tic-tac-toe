package by.xxx.pupil.ai.combinations

import java.util.regex.Pattern

class CombinationPatterns {
    val noughtsPatternsToCombinations: Map<Pattern, GeneralCombination>
    val crossesPatternsToCombinations: Map<Pattern, GeneralCombination>

    companion object {
        val FIVE_NOUGHTS_PATTERN: Pattern = Pattern.compile("00000")
        val STRAIGHT_FOUR_NOUGHTS_PATTERN: Pattern = Pattern.compile(" 0000 ")
        val FOUR_NOUGHTS_PATTERN: Pattern = Pattern.compile("( 0000x)|(x0000 )|(^0000 )|( 0000$)|( 0000#)|(#0000 )")
        val THREE_NOUGHTS_PATTERN: Pattern = Pattern.compile(" 000 ")
        val BROKEN_THREE_NOUGHTS_PATTERN: Pattern = Pattern.compile("( 0 00 )|( 00 0 )")
        val TWO_NOUGHTS_PATTERN: Pattern = Pattern.compile(" 00 ")
        val ONE_NOUGHT_PATTERN: Pattern = Pattern.compile(" 0 ")

        val FIVE_CROSSES_PATTERN: Pattern = Pattern.compile("xxxxx")
        val STRAIGHT_FOUR_CROSSES_PATTERN: Pattern = Pattern.compile(" xxxx ")
        val FOUR_CROSSES_PATTERN: Pattern = Pattern.compile("( xxxx0)|(0xxxx )|(^xxxx )|( xxxx$)|( 0000#)|(#0000 )")
        val THREE_CROSSES_PATTERN: Pattern = Pattern.compile(" xxx ")
        val BROKEN_THREE_CROSSES_PATTERN: Pattern = Pattern.compile("( x xx )|( xx x )")
        val TWO_CROSSES_PATTERN: Pattern = Pattern.compile(" xx ")
        val ONE_CROSS_PATTERN: Pattern = Pattern.compile(" x ")
    }

    init {
        noughtsPatternsToCombinations = hashMapOf(
                FIVE_NOUGHTS_PATTERN to GeneralCombination.FIVE,
                STRAIGHT_FOUR_NOUGHTS_PATTERN to GeneralCombination.STRAIGHT_FOUR,
                FOUR_NOUGHTS_PATTERN to GeneralCombination.FOUR,
                THREE_NOUGHTS_PATTERN to GeneralCombination.THREE,
                BROKEN_THREE_NOUGHTS_PATTERN to GeneralCombination.BROKEN_THREE,
                TWO_NOUGHTS_PATTERN to GeneralCombination.TWO,
                ONE_NOUGHT_PATTERN to GeneralCombination.ONE
        )

        crossesPatternsToCombinations = hashMapOf(
                FIVE_CROSSES_PATTERN to GeneralCombination.FIVE,
                STRAIGHT_FOUR_CROSSES_PATTERN to GeneralCombination.STRAIGHT_FOUR,
                FOUR_CROSSES_PATTERN to GeneralCombination.FOUR,
                THREE_CROSSES_PATTERN to GeneralCombination.THREE,
                BROKEN_THREE_CROSSES_PATTERN to GeneralCombination.BROKEN_THREE,
                TWO_CROSSES_PATTERN to GeneralCombination.TWO,
                ONE_CROSS_PATTERN to GeneralCombination.ONE
        )
    }
}