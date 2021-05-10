package by.xxx.pupil.ai.minimax.evaluate

import by.xxx.pupil.ai.combinations.CombinationsFinder
import by.xxx.pupil.ai.combinations.GeneralCombination
import by.xxx.pupil.winning.WinnerFinder
import by.xxx.pupil.ai.hashing.ScoreCache
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.stream.Collectors

class TrickyEvaluator(
        private val combinationsFinder: CombinationsFinder,
        private val scoreCache: ScoreCache,
        private val winnerFinder: WinnerFinder
) : Evaluator {

    private val evaluationScores: Map<GeneralCombination, Int>

    override fun evaluate(board: Board, lastMove: Move): Int {
        if (winnerFinder.isMoveLeadToWin(board, lastMove)) {
            return if (Player.CROSSES == lastMove.player) {
                PERSON_WIN_SCORE
            } else {
                AI_WIN_SCORE
            }
        }
        val lines = board.allLines.stream()
                .filter { cs: String? -> StringUtils.isNotBlank(cs) }
                .collect(Collectors.toList())

        val aiScore = lines.asSequence()
                .flatMap { line -> combinationsFinder.findNoughtsCombinations(line)!!.asSequence() }
                .sumBy { generalCombination -> evaluationScores.get(generalCombination)!! }

        val personScore = lines.asSequence()
                .flatMap { line -> combinationsFinder.findCrossesCombinations(line)!!.asSequence() }
                .sumBy { generalCombination -> evaluationScores.get(generalCombination)!! }

        return aiScore + personScore * (-1)
    }

    override fun evaluate(board: Board, lastMove: Move, hash: Long): Int {
        var score = scoreCache.getScore(board)
        if (score == null) {
            score = evaluate(board, lastMove)
            scoreCache.putScore(hash, score)
        }
        return score
    }

    companion object {
        private const val AI_WIN_SCORE = 10000000
        private const val PERSON_WIN_SCORE = -10000000
        private const val DRAW_SCORE = 0
    }

    init {
        val map: MutableMap<GeneralCombination, Int> = HashMap()
        map[GeneralCombination.FIVE] = 10000000
        map[GeneralCombination.STRAIGHT_FOUR] = 950000
        map[GeneralCombination.THREE] = 10000
        map[GeneralCombination.BROKEN_THREE] = 8000
        map[GeneralCombination.FOUR] = 6000
        map[GeneralCombination.TWO] = 1000
        map[GeneralCombination.ONE] = 100

        evaluationScores = Collections.unmodifiableMap(map)
    }
}