package by.xxx.pupil

import by.xxx.pupil.ai.AIPlayer
import by.xxx.pupil.ai.hashing.InMemoryCache
import by.xxx.pupil.ai.hashing.ScoreCache
import by.xxx.pupil.ai.hashing.ZobristHashing
import by.xxx.pupil.ai.minimax.Minimax
import by.xxx.pupil.ai.minimax.MinimaxBasedAI
import by.xxx.pupil.ai.minimax.evaluate.Evaluator
import by.xxx.pupil.ai.minimax.evaluate.TrickyEvaluator
import by.xxx.pupil.ai.minimax.findmoves.InRadiusMovesFinder
import by.xxx.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class Cfg {

    @Bean
    open fun combinationPatterns(): CombinationPatterns {
        return CombinationPatterns()
    }

    @Bean
    open fun combinationsFinder(): CombinationsFinder {
        return CombinationsFinder(combinationPatterns())
    }

    @Bean
    open fun zobristHashing(): ZobristHashing {
        return ZobristHashing(10, 10)
    }

    @Bean
    open fun scoreCache(): ScoreCache {
        return InMemoryCache(zobristHashing())
    }

    @Bean
    open fun evaluator(): Evaluator {
        return TrickyEvaluator(combinationsFinder(), scoreCache(), winnerFinder())
    }

    @Bean
    open fun inRadiusMovesFinder(): InRadiusMovesFinder {
        return InRadiusMovesFinder(Constants.AVAILABILITY_RADIUS)
    }

    @Bean
    open fun shallowSearchMovesFinder(): ShallowSearchMovesFinder {
        return ShallowSearchMovesFinder(inRadiusMovesFinder(), evaluator(), zobristHashing())
    }

    //    @Bean
    //    public MovesFinder threatMovesFinder() {
    //        return new ThreatMovesFinder(inRadiusMovesFinder(), combinationsFinder());
    //    }
    @Bean
    open fun winnerFinder(): WinnerFinder {
        return WinnerFinder()
    }

    @Bean
    open fun minimax(): Minimax {
        return Minimax(Constants.DEFAULT_MINIMAX_DEPTH_LIMIT, shallowSearchMovesFinder(), evaluator(), winnerFinder(), zobristHashing())
    }

    @Bean
    open fun aiPlayer(): AIPlayer {
        return MinimaxBasedAI(shallowSearchMovesFinder(), evaluator(), winnerFinder(), minimax(), zobristHashing())
    }
}