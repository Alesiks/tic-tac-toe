package by.pupil

import by.pupil.ai.AIPlayer
import by.pupil.ai.combinations.CombinationPatterns
import by.pupil.ai.combinations.CombinationsFinder
import by.pupil.ai.hashing.NoCache
import by.pupil.ai.hashing.ScoreCache
import by.pupil.ai.hashing.ZobristHashing
import by.pupil.ai.minimax.Minimax
import by.pupil.ai.minimax.MinimaxBasedAI
import by.pupil.ai.minimax.evaluate.StateEvaluator
import by.pupil.ai.minimax.evaluate.TrickyStateEvaluator
import by.pupil.ai.minimax.findmoves.InRadiusMovesFinder
import by.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder
import by.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.pupil.winning.WinnerFinder
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
        return TrickyStateEvaluator(combinationsFinder(), winnerFinder())
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
        return ShallowSearchMovesFinder(inRadiusMovesFinder(), moveEvaluator(), 10)
    }

    @Bean
    open fun winnerFinder(): WinnerFinder {
        return WinnerFinder()
    }

    @Bean
    open fun minimax(): Minimax {
        return Minimax(shallowSearchMovesFinder(), evaluator(), winnerFinder())
    }

    @Bean
    open fun aiPlayer(): AIPlayer {
        return MinimaxBasedAI(shallowSearchMovesFinder(), winnerFinder(), minimax())
    }
}