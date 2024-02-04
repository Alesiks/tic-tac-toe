package by.pupil.ai.minimax.evaluate

import by.pupil.ai.combinations.CombinationsFinder
import by.pupil.ai.combinations.GeneralCombination
import by.pupil.model.Board
import by.pupil.model.Move
import by.pupil.model.Player
import by.pupil.winning.WinnerFinder
import org.apache.commons.lang3.StringUtils

class TrickyStateEvaluator(
    private val combinationsFinder: CombinationsFinder,
    private val winnerFinder: WinnerFinder,
) : StateEvaluator {
    private val evaluationScores: Map<GeneralCombination, Int> =
        mapOf(
            GeneralCombination.FIVE to 10000000,
            GeneralCombination.STRAIGHT_FOUR to 950000,
            GeneralCombination.THREE to 10000,
            GeneralCombination.BROKEN_THREE to 8000,
            GeneralCombination.FOUR to 6000,
            GeneralCombination.TWO to 1000,
            GeneralCombination.ONE to 100,
        )

    override fun evaluate(
        board: Board,
        lastMove: Move,
    ): Int {
        if (winnerFinder.isMoveLeadToWin(board, lastMove)) {
            return if (Player.CROSSES == lastMove.player) {
                PERSON_WIN_SCORE
            } else {
                AI_WIN_SCORE
            }
        }
        val lines =
            board.allLines.asSequence()
                .filter { cs: String? -> StringUtils.isNotBlank(cs) }
                .toList()

        val aiScore =
            lines.asSequence()
                .flatMap { line -> combinationsFinder.findNoughtsCombinations(line).asSequence() }
                .sumBy { generalCombination -> evaluationScores.get(generalCombination)!! }

        val personScore =
            lines.asSequence()
                .flatMap { line -> combinationsFinder.findCrossesCombinations(line).asSequence() }
                .sumBy { generalCombination -> evaluationScores.get(generalCombination)!! }

        return aiScore + personScore * (-1)
    }

    companion object {
        private const val AI_WIN_SCORE = 10000000
        private const val PERSON_WIN_SCORE = -10000000
        private const val DRAW_SCORE = 0
    }
}
