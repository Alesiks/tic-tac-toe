package by.xxx.pupil;

import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.ai.minimax.Evaluator;
import by.xxx.pupil.ai.minimax.MinimaxBasedAI;
import by.xxx.pupil.ai.minimax.PossibleMovesFinder;
import by.xxx.pupil.ai.minimax.impl.DefaultEvaluator;
import by.xxx.pupil.ai.minimax.impl.DefaultMovesFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cfg {

    @Bean
    public Evaluator evaluator() {
        return new DefaultEvaluator();
    }

    @Bean
    public PossibleMovesFinder possibleMovesFinder() {
        return new DefaultMovesFinder();
    }

    @Bean
    public WinnerFinder winnerFinder() {
        return new WinnerFinder();
    }

    @Bean
    public AIPlayer aiPlayer() {
        return new MinimaxBasedAI(possibleMovesFinder(), evaluator(), winnerFinder(), Constants.DEFAULT_MINIMAX_DEPTH_LIMIT);
    }

}
