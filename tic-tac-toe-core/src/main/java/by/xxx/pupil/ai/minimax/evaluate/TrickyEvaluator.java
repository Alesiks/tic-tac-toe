package by.xxx.pupil.ai.minimax.evaluate;

import by.xxx.pupil.CombinationsFinder;
import by.xxx.pupil.GeneralCombination;
import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.hashing.ScoreCache;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrickyEvaluator implements Evaluator {

    private static final int AI_WIN_SCORE = 10000000;
    private static final int PERSON_WIN_SCORE = -10000000;
    private static final int DRAW_SCORE = 0;

    private final CombinationsFinder combinationsFinder;
    private final ScoreCache scoreCache;
    private final WinnerFinder winnerFinder;

    private final Map<GeneralCombination, Integer> evaluationScores;

    public TrickyEvaluator(
            CombinationsFinder combinationsFinder,
            ScoreCache scoreCache,
            WinnerFinder winnerFinder
    ) {
        Validate.notNull(combinationsFinder, "combinationsFinder is null");
        Validate.notNull(scoreCache, "scoreCache");
        Validate.notNull(winnerFinder, "winnerFinder");

        this.combinationsFinder = combinationsFinder;
        this.scoreCache = scoreCache;
        this.winnerFinder = winnerFinder;

        Map<GeneralCombination, Integer> map = new HashMap<>();
        map.put(GeneralCombination.FIVE, 10000000);
        map.put(GeneralCombination.STRAIGHT_FOUR, 950000);
        map.put(GeneralCombination.THREE, 10000);
        map.put(GeneralCombination.BROKEN_THREE, 8000);
        map.put(GeneralCombination.FOUR, 6000);
        map.put(GeneralCombination.TWO, 1000);
        map.put(GeneralCombination.ONE, 100);

        evaluationScores = Collections.unmodifiableMap(map);
    }

    @Override
    public int evaluate(Board board, Move lastMove) {
        if (winnerFinder.isMoveLeadToWin(board, lastMove)) {
            if (Player.CROSSES.equals(lastMove.getPlayer())) {
                return PERSON_WIN_SCORE;
            } else {
                return AI_WIN_SCORE;
            }
        }

        List<String> lines = board.getAllLines().stream()
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());

        int aiScore = lines.stream()
                .flatMap(line -> combinationsFinder.findNoughtsCombinations(line).stream())
                .mapToInt(
                        generalCombination -> evaluationScores.getOrDefault(generalCombination, DRAW_SCORE)
                )
                .sum();

        int personScore = lines.stream()
                .flatMap(line -> combinationsFinder.findCrossesCombinations(line).stream())
                .mapToInt(
                        generalCombination -> evaluationScores.getOrDefault(generalCombination, DRAW_SCORE) * -1
                )
                .sum();
//
//            if(lastMove.isPerson()) {
//
//            } else {
//
//            }

        return aiScore + personScore;
    }

    @Override
    public int evaluate(Board board, Move lastMove, long hash) {
        Integer score = scoreCache.getScore(board);

        if (score == null) {
            score = evaluate(board, lastMove);
            scoreCache.putScore(hash, score);
        }
        return score;
    }

}
