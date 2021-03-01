package by.xxx.pupil;

import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.ai.hashing.InMemoryCache;
import by.xxx.pupil.ai.hashing.ScoreCache;
import by.xxx.pupil.ai.hashing.ZobristHashing;
import by.xxx.pupil.ai.minimax.evaluate.Evaluator;
import by.xxx.pupil.ai.minimax.Minimax;
import by.xxx.pupil.ai.minimax.MinimaxBasedAI;
import by.xxx.pupil.ai.minimax.findmoves.InRadiusMovesFinder;
import by.xxx.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder;
import by.xxx.pupil.ai.minimax.evaluate.TrickyEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.xxx.pupil.Constants.AVAILABILITY_RADIUS;

@Configuration
public class Cfg {

    @Bean
    public CombinationPatterns combinationPatterns() {
        return new CombinationPatterns();
    }

    @Bean
    public CombinationsFinder combinationsFinder() {
        return new CombinationsFinder(combinationPatterns());
    }

    @Bean
    public ZobristHashing zobristHashing() {
        return new ZobristHashing(10, 10);
    }

    @Bean
    public ScoreCache scoreCache() {
        return new InMemoryCache(zobristHashing());
    }

    @Bean
    public Evaluator evaluator() {
        return new TrickyEvaluator(combinationsFinder(), scoreCache(), winnerFinder());
    }

    @Bean
    public InRadiusMovesFinder inRadiusMovesFinder() {
        return new InRadiusMovesFinder(AVAILABILITY_RADIUS);
    }

    @Bean
    public ShallowSearchMovesFinder shallowSearchMovesFinder() {
        return new ShallowSearchMovesFinder(inRadiusMovesFinder(), evaluator(), zobristHashing());
    }

//    @Bean
//    public MovesFinder threatMovesFinder() {
//        return new ThreatMovesFinder(inRadiusMovesFinder(), combinationsFinder());
//    }

    @Bean
    public WinnerFinder winnerFinder() {
        return new WinnerFinder();
    }

    @Bean
    public Minimax minimax() {
        return new Minimax(Constants.DEFAULT_MINIMAX_DEPTH_LIMIT, shallowSearchMovesFinder(), evaluator(), winnerFinder(), zobristHashing());
    }

    @Bean
    public AIPlayer aiPlayer() {
        return new MinimaxBasedAI(shallowSearchMovesFinder(), evaluator(), winnerFinder(), minimax(), zobristHashing());
    }

}
