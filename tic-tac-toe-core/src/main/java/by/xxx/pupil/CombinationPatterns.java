package by.xxx.pupil;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CombinationPatterns {

    private static final String FIVE_AI = "00000";
    private static final Pattern fivePatternAi = Pattern.compile(FIVE_AI);

    private static final String STRAIGHT_FOUR_AI = " 0000 ";
    public static final Pattern straightFourPatternAI = Pattern.compile(STRAIGHT_FOUR_AI);

    private static final String FOUR_AI = "( 0000x)|(x0000 )|(^0000 )|( 0000$)";
    public static final Pattern fourPatternAI = Pattern.compile(FOUR_AI);

    private static final String THREE_AI = " 000 ";
    public static final Pattern threePatternAI = Pattern.compile(THREE_AI);

    private static final String BROKEN_THREE_AI = "( 0 00 )|( 00 0 )";
    public static final Pattern brokenThreePatternAI = Pattern.compile(BROKEN_THREE_AI);


    private static final String FIVE_PERSON = "xxxxx";
    private static final Pattern fivePatternPerson = Pattern.compile(FIVE_PERSON);

    private static final String STRAIGHT_FOUR_PERSON = " xxxx ";
    public static final Pattern straightFourPatternPerson = Pattern.compile(STRAIGHT_FOUR_PERSON);

    private static final String FOUR_PERSON = "( xxxx0)|(0xxxx )|(^xxxx )|( xxxx$)";
    public static final Pattern fourPatternPerson = Pattern.compile(FOUR_PERSON);

    private static final String THREE_PERSON = " xxx ";
    public static final Pattern threePatternPerson = Pattern.compile(THREE_PERSON);

    private static final String BROKEN_THREE_PERSON = "( x xx )|( xx x )";
    public static final Pattern brokenThreePatternPerson = Pattern.compile(BROKEN_THREE_PERSON);

    public static final Map<Pattern, GeneralCombinationNames> aiPatternsToCombinations = Stream.of(new Object[][]{
            {fivePatternAi, GeneralCombinationNames.FIVE},
            {straightFourPatternAI, GeneralCombinationNames.STRAIGHT_FOUR},
            {fourPatternAI, GeneralCombinationNames.FOUR},
            {threePatternAI, GeneralCombinationNames.THREE},
            {brokenThreePatternAI, GeneralCombinationNames.BROKEN_THREE}
    }).collect(toMap(data -> (Pattern) data[0], data -> (GeneralCombinationNames) data[1]));

    public static final Map<Pattern, GeneralCombinationNames> personPatternsToCombinations = Stream.of(new Object[][]{
            {fivePatternPerson, GeneralCombinationNames.FIVE},
            {straightFourPatternPerson, GeneralCombinationNames.STRAIGHT_FOUR},
            {fourPatternPerson, GeneralCombinationNames.FOUR},
            {threePatternPerson, GeneralCombinationNames.THREE},
            {brokenThreePatternPerson, GeneralCombinationNames.BROKEN_THREE}
    }).collect(toMap(data -> (Pattern) data[0], data -> (GeneralCombinationNames) data[1]));


    public static final List<Pattern> aiPatterns = Stream.of(
            fivePatternAi,
            straightFourPatternAI,
            fourPatternAI,
            threePatternAI,
            brokenThreePatternAI
    ).collect(toList());

    public static final List<Pattern> personPatterns = Stream.of(
            fivePatternPerson,
            straightFourPatternPerson,
            fourPatternPerson,
            threePatternPerson,
            brokenThreePatternPerson
    ).collect(toList());

}
