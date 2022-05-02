package by.pupil

import by.pupil.ai.AIPlayer
import by.pupil.ai.combinations.CombinationPatterns
import by.pupil.ai.combinations.CombinationsFinder
import by.pupil.ai.minimax.Minimax
import by.pupil.ai.minimax.MinimaxBasedAI
import by.pupil.ai.minimax.evaluate.DefaultStateEvaluator
import by.pupil.ai.minimax.evaluate.StateEvaluator
import by.pupil.ai.minimax.evaluate.TrickyStateEvaluator
import by.pupil.ai.minimax.findmoves.AllAvailableMovesFinder
import by.pupil.ai.minimax.findmoves.InRadiusMovesFinder
import by.pupil.ai.minimax.findmoves.MovesFinder
import by.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder
import by.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.pupil.winning.WinnerFinder
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val coreModule = module(createdAtStart = true) {

    single { CombinationPatterns() }
    single { CombinationsFinder(combinationPatterns = get()) }

    single { WinnerFinder() }

    single<MovesFinder>(named("in_radius_moves_finder")) { InRadiusMovesFinder(availabilityRadius = Constants.AVAILABILITY_RADIUS) }
    single<MovesFinder>(named("all_available_moves_finder")) { AllAvailableMovesFinder() }

    single<StateEvaluator>(named("default_state_evaluator")) { DefaultStateEvaluator(winnerFinder = get()) }
    single<StateEvaluator>(named("tricky_state_evaluator")) {
        TrickyStateEvaluator(combinationsFinder = get(), winnerFinder = get())
    }

    single { MoveEvaluator(winnerFinder = get()) }
}

val minimaxModule = module(createdAtStart = true) {

    single<MovesFinder>(named("shallow_search_moves_finder")) {
        ShallowSearchMovesFinder(
            baseMovesFinder = get(qualifier("in_radius_moves_finder")),
            moveEvaluator = get(),
            movesNumber = 10
        )
    }

    single {
        Minimax(
            movesFinder = get(qualifier("shallow_search_moves_finder")),
            evaluator = get(qualifier("tricky_state_evaluator")),
            winnerFinder = get()
        )
    }

    single<AIPlayer> {
        MinimaxBasedAI(
            movesFinder = get(qualifier("shallow_search_moves_finder")),
            winnerFinder = get(),
            minimax = get(),
        )
    }
}
