package by.xxx.pupil;

import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.ai.hashing.InMemoryCache;
import by.xxx.pupil.ai.hashing.ScoreCache;
import by.xxx.pupil.ai.hashing.ZobristHashing;
import by.xxx.pupil.ai.minimax.Evaluator;
import by.xxx.pupil.ai.minimax.Minimax;
import by.xxx.pupil.ai.minimax.MinimaxBasedAI;
import by.xxx.pupil.ai.minimax.MovesFinder;
import by.xxx.pupil.ai.minimax.impl.DefaultEvaluator;
import by.xxx.pupil.ai.minimax.impl.InRadiusMovesFinder;
import by.xxx.pupil.ai.minimax.impl.ThreatMovesFinder;
import by.xxx.pupil.ai.minimax.impl.TrickyEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.xxx.pupil.Constants.AVAILABILITY_RADIUS;

@Configuration
public class Cfg {

//    @Bean
//    public Evaluator evaluator() {
//        return new DefaultEvaluator();
//    }

    @Bean
    public CombinationsFinder combinationsFinder() {
        return new CombinationsFinder();
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
        return new Minimax(Constants.DEFAULT_MINIMAX_DEPTH_LIMIT, inRadiusMovesFinder(), evaluator(), winnerFinder());
    }

    @Bean
    public AIPlayer aiPlayer() {
        return new MinimaxBasedAI(inRadiusMovesFinder(), evaluator(), winnerFinder(), minimax());
    }

}
