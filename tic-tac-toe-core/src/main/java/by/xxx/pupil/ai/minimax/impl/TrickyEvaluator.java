package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.CombinationsFinder;
import by.xxx.pupil.GeneralCombinationNames;
import by.xxx.pupil.ai.minimax.Evaluator;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.stream.Collectors;

public class TrickyEvaluator implements Evaluator {

    private static final int AI_WIN_SCORE = 1000000;
    private static final int PERSON_WIN_SCORE = -1000000;
    private static final int DRAW_SCORE = 0;

    private static final int AI_FOUR_OPEN_IN_A_ROW = 950000;  // *0000*
    private static final int AI_THREE_OPEN_IN_A_ROW = 10000; // *000*
    private static final int AI_BROKEN_THREE = 8000; //  // *0*00* - broken three
    private static final int AI_FOUR_PARTLY_OPEN_IN_A_ROW = 6000; // *x0000* or *0000x*

    private static final int PERSON_FOUR_OPEN_IN_A_ROW = -950000;  // *xxxx*  - straight four
    private static final int PERSON_THREE_OPEN_IN_A_ROW = -10000; // **xxx** or **xxx*0 or 0*xxx** - three
    private static final int PERSON_BROKEN_THREE = 8000; //  // *0*00* - broken three
    private static final int PERSON_FOUR_PARTLY_OPEN_IN_A_ROW = -6000; // *oxxxx* or *xxxx0*  - four
    // *x*xx* - broken three

    private final CombinationsFinder combinationsFinder;

    public TrickyEvaluator(CombinationsFinder combinationsFinder) {
        Validate.notNull(combinationsFinder, "combinationsFinder is null");

        this.combinationsFinder = combinationsFinder;
    }

    @Override
    public int evaluate(Board board, GameState gameState, Move lastMove) {
        if (gameState == GameState.NOUGHT_WIN) {
            return AI_WIN_SCORE;
        } else if (gameState == GameState.CROSS_WIN) {
            return PERSON_WIN_SCORE;
        } else {
            int score = DRAW_SCORE;
            List<String> lines = board.getAllLines().stream()
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            int aiScore = lines.stream()
                    .flatMap(line -> combinationsFinder.findAICombination(line).stream())
                    .mapToInt(generalCombination -> {
                        if (GeneralCombinationNames.STRAIGHT_FOUR.equals(generalCombination)) {
                            return AI_FOUR_OPEN_IN_A_ROW;
                        } else if (GeneralCombinationNames.FOUR.equals(generalCombination)) {
                            return AI_FOUR_PARTLY_OPEN_IN_A_ROW;
                        } else if (GeneralCombinationNames.THREE.equals(generalCombination)) {
                            return AI_THREE_OPEN_IN_A_ROW;
                        } else if (GeneralCombinationNames.BROKEN_THREE.equals(generalCombination)) {
                            return AI_BROKEN_THREE;
                        } else {
                            return DRAW_SCORE;
                        }
                    })
                    .sum();

            int personScore = lines.stream()
                    .flatMap(line -> combinationsFinder.findAICombination(line).stream())
                    .mapToInt(generalCombination -> {
                        if (GeneralCombinationNames.STRAIGHT_FOUR.equals(generalCombination)) {
                            return PERSON_FOUR_OPEN_IN_A_ROW;
                        } else if (GeneralCombinationNames.FOUR.equals(generalCombination)) {
                            return PERSON_FOUR_PARTLY_OPEN_IN_A_ROW;
                        } else if (GeneralCombinationNames.THREE.equals(generalCombination)) {
                            return PERSON_THREE_OPEN_IN_A_ROW;
                        } else if (GeneralCombinationNames.BROKEN_THREE.equals(generalCombination)) {
                            return PERSON_BROKEN_THREE;
                        } else {
                            return DRAW_SCORE;
                        }
                    })
                    .sum();

            return aiScore + personScore;
        }
    }

}
