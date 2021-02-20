package by.xxx.pupil;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CombinationPatterns {

    private static final String STRAIGHT_FOUR_AI = " 0000 ";
    public static final Pattern straightFourPatternAI = Pattern.compile(STRAIGHT_FOUR_AI);

    private static final String FOUR_AI = "( 0000x)|(x0000 )|(^0000 )|( 0000$)";
    public static final Pattern fourPatternAI = Pattern.compile(FOUR_AI);

    private static final String THREE_AI = " 000 ";
    public static final Pattern threePatternAI = Pattern.compile(THREE_AI);

    private static final String BROKEN_THREE_AI = "( 0 00 )|( 00 0 )";
    public static final Pattern brokenThreePatternAI = Pattern.compile(BROKEN_THREE_AI);


    private static final String STRAIGHT_FOUR_PERSON = " xxxx ";
    public static final Pattern straightFourPatternPerson = Pattern.compile(STRAIGHT_FOUR_PERSON);

    private static final String FOUR_PERSON = "( xxxx0)|(0xxxx )|(^xxxx )|( xxxx$)";
    public static final Pattern fourPatternPerson = Pattern.compile(FOUR_PERSON);

    private static final String THREE_PERSON = " xxx ";
    public static final Pattern threePatternPerson = Pattern.compile(THREE_PERSON);

    private static final String BROKEN_THREE_PERSON = "( x xx )|( xx x )";
    public static final Pattern brokenThreePatternPerson = Pattern.compile(BROKEN_THREE_PERSON);

    public static final Map<Pattern, GeneralCombinationNames> aiPatternsToCombinations = Stream.of(new Object[][]{
            {straightFourPatternAI, GeneralCombinationNames.STRAIGHT_FOUR},
            {fourPatternAI, GeneralCombinationNames.STRAIGHT_FOUR},
            {threePatternAI, GeneralCombinationNames.STRAIGHT_FOUR},
            {brokenThreePatternAI, GeneralCombinationNames.STRAIGHT_FOUR}
    }).collect(toMap(data -> (Pattern) data[0], data -> (GeneralCombinationNames) data[1]));

    public static final Map<Pattern, GeneralCombinationNames> personPatternsToCombinations = Stream.of(new Object[][]{
            {straightFourPatternPerson, GeneralCombinationNames.STRAIGHT_FOUR},
            {fourPatternPerson, GeneralCombinationNames.STRAIGHT_FOUR},
            {threePatternPerson, GeneralCombinationNames.STRAIGHT_FOUR},
            {brokenThreePatternPerson, GeneralCombinationNames.STRAIGHT_FOUR}
    }).collect(toMap(data -> (Pattern) data[0], data -> (GeneralCombinationNames) data[1]));


    public static final List<Pattern> aiPatterns = Stream.of(
            straightFourPatternAI,
            fourPatternAI,
            threePatternAI,
            brokenThreePatternAI
    ).collect(toList());

    public static final List<Pattern> personPatterns = Stream.of(
            straightFourPatternPerson,
            fourPatternPerson,
            threePatternPerson,
            brokenThreePatternPerson
    ).collect(toList());


}
