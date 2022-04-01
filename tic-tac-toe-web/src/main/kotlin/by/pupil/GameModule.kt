package by.pupil

import by.pupil.Constants.AVAILABILITY_RADIUS
import by.pupil.ai.AIPlayer
import by.pupil.ai.combinations.CombinationPatterns
import by.pupil.ai.combinations.CombinationsFinder
import by.pupil.ai.minimax.Minimax
import by.pupil.ai.minimax.MinimaxBasedAI
import by.pupil.ai.minimax.evaluate.StateEvaluator
import by.pupil.ai.minimax.evaluate.TrickyStateEvaluator
import by.pupil.ai.minimax.findmoves.InRadiusMovesFinder
import by.pupil.ai.minimax.findmoves.MovesFinder
import by.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder
import by.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.pupil.converter.RequestConverter
import by.pupil.winning.WinnerFinder
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val gameModule = module(createdAtStart = true) {
    single { RequestConverter() }
    single { WinnerFinder() }

    single { MoveEvaluator(get()) }

    single { CombinationPatterns() }
    single { CombinationsFinder(get()) }

    single<StateEvaluator> { TrickyStateEvaluator(get(), get()) }

    single<MovesFinder>(qualifier("inRadius")) {
        InRadiusMovesFinder(AVAILABILITY_RADIUS)
    }

    single<MovesFinder>(qualifier("shallowSearch")){
        ShallowSearchMovesFinder(get(qualifier("inRadius")), get(), 10)
    }

    single { Minimax(get(qualifier("shallowSearch")), get(), get()) }

    single<AIPlayer> { MinimaxBasedAI(get(qualifier("shallowSearch")), get(), get()) }

}