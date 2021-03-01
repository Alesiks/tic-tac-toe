package by.xxx.pupil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CombinationPatterns {

    public static final Pattern FIVE_NOUGHTS_PATTERN = Pattern.compile("00000");
    public static final Pattern STRAIGHT_FOUR_NOUGHTS_PATTERN = Pattern.compile(" 0000 ");
    public static final Pattern FOUR_NOUGHTS_PATTERN = Pattern.compile("( 0000x)|(x0000 )|(^0000 )|( 0000$)");
    public static final Pattern THREE_NOUGHTS_PATTERN = Pattern.compile(" 000 ");
    public static final Pattern BROKEN_THREE_NOUGHTS_PATTERN = Pattern.compile("( 0 00 )|( 00 0 )");
    public static final Pattern TWO_NOUGHTS_PATTERN = Pattern.compile(" 00 ");
    public static final Pattern ONE_NOUGHT_PATTERN = Pattern.compile(" 0 ");

    public static final Pattern FIVE_CROSSES_PATTERN = Pattern.compile("xxxxx");
    public static final Pattern STRAIGHT_FOUR_CROSSES_PATTERN = Pattern.compile(" xxxx ");
    public static final Pattern FOUR_CROSSES_PATTERN = Pattern.compile("( xxxx0)|(0xxxx )|(^xxxx )|( xxxx$)");
    public static final Pattern THREE_CROSSES_PATTERN = Pattern.compile(" xxx ");
    public static final Pattern BROKEN_THREE_CROSSES_PATTERN = Pattern.compile("( x xx )|( xx x )");
    public static final Pattern TWO_CROSSES_PATTERN = Pattern.compile(" xx ");
    public static final Pattern ONE_CROSS_PATTERN = Pattern.compile(" x ");

    private final Map<Pattern, GeneralCombination> noughtsPatternsToCombinations;
    private final Map<Pattern, GeneralCombination> crossesPatternsToCombinations;

    public CombinationPatterns() {
        Map<Pattern, GeneralCombination> noughtsMap = new HashMap<>();
        noughtsMap.put(FIVE_NOUGHTS_PATTERN, GeneralCombination.FIVE);
        noughtsMap.put(STRAIGHT_FOUR_NOUGHTS_PATTERN, GeneralCombination.STRAIGHT_FOUR);
        noughtsMap.put(FOUR_NOUGHTS_PATTERN, GeneralCombination.FOUR);
        noughtsMap.put(THREE_NOUGHTS_PATTERN, GeneralCombination.THREE);
        noughtsMap.put(BROKEN_THREE_NOUGHTS_PATTERN, GeneralCombination.BROKEN_THREE);
        noughtsMap.put(TWO_NOUGHTS_PATTERN, GeneralCombination.TWO);
        noughtsMap.put(ONE_NOUGHT_PATTERN, GeneralCombination.ONE);

        Map<Pattern, GeneralCombination> crossesMap = new HashMap<>();
        crossesMap.put(FIVE_CROSSES_PATTERN, GeneralCombination.FIVE);
        crossesMap.put(STRAIGHT_FOUR_CROSSES_PATTERN, GeneralCombination.STRAIGHT_FOUR);
        crossesMap.put(FOUR_CROSSES_PATTERN, GeneralCombination.FOUR);
        crossesMap.put(THREE_CROSSES_PATTERN, GeneralCombination.THREE);
        crossesMap.put(BROKEN_THREE_CROSSES_PATTERN, GeneralCombination.BROKEN_THREE);
        crossesMap.put(TWO_CROSSES_PATTERN, GeneralCombination.TWO);
        crossesMap.put(ONE_CROSS_PATTERN, GeneralCombination.ONE);

        this.noughtsPatternsToCombinations = Collections.unmodifiableMap(noughtsMap);
        this.crossesPatternsToCombinations = Collections.unmodifiableMap(crossesMap);
    }

    public Map<Pattern, GeneralCombination> getNoughtsPatternsToCombinations() {
        return noughtsPatternsToCombinations;
    }

    public Map<Pattern, GeneralCombination> getCrossesPatternsToCombinations() {
        return crossesPatternsToCombinations;
    }

}
