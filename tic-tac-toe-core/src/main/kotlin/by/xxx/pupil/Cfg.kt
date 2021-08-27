package by.xxx.pupil

import by.xxx.pupil.ai.AIPlayer
import by.xxx.pupil.ai.hashing.InMemoryCache
import by.xxx.pupil.ai.hashing.ScoreCache
import by.xxx.pupil.ai.hashing.ZobristHashing
import by.xxx.pupil.ai.minimax.Minimax
import by.xxx.pupil.ai.minimax.MinimaxBasedAI
import by.xxx.pupil.ai.minimax.evaluate.StateEvaluator
import by.xxx.pupil.ai.minimax.evaluate.TrickyStateEvaluator
import by.xxx.pupil.ai.minimax.findmoves.InRadiusMovesFinder
import by.xxx.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder
import by.xxx.pupil.ai.combinations.CombinationPatterns
import by.xxx.pupil.ai.combinations.CombinationsFinder
import by.xxx.pupil.ai.hashing.NoCache
import by.xxx.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.xxx.pupil.winning.WinnerFinder
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

//    @Bean
//    open fun scoreCache(): ScoreCache {
//        return InMemoryCache(zobristHashing())
//    }

    @Bean
    open fun scoreCache(): ScoreCache {
        return NoCache()
    }

    @Bean
    open fun evaluator(): StateEvaluator {
        return TrickyStateEvaluator(combinationsFinder(), scoreCache(), winnerFinder())
    }

    @Bean
    open fun inRadiusMovesFinder(): InRadiusMovesFinder {
        return InRadiusMovesFinder(Constants.AVAILABILITY_RADIUS)
    }

    @Bean
    open fun moveEvaluator(): MoveEvaluator {
        return MoveEvaluator(winnerFinder())
    }

    @Bean
    open fun shallowSearchMovesFinder(): ShallowSearchMovesFinder {
        return ShallowSearchMovesFinder(inRadiusMovesFinder(), moveEvaluator())
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
        return Minimax(shallowSearchMovesFinder(), evaluator(), winnerFinder(), zobristHashing())
    }

    @Bean
    open fun aiPlayer(): AIPlayer {
        return MinimaxBasedAI(shallowSearchMovesFinder(), winnerFinder(), minimax(), zobristHashing())
    }
}