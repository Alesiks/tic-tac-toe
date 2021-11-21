package by.xx.pupil

import by.xxx.pupil.ai.AIPlayer
import by.xxx.pupil.ai.minimax.MinimaxBasedAI
import by.xxx.pupil.ai.minimax.findmoves.AllAvailableMovesFinder
import by.xxx.pupil.ai.minimax.findmoves.InRadiusMovesFinder
import by.xxx.pupil.ai.minimax.findmoves.MovesFinder
import by.xxx.pupil.ai.minimax.findmoves.ShallowSearchMovesFinder
import by.xxx.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.xxx.pupil.winning.WinnerFinder

class AIAlgorithmsFactory {

    fun getAIAlgorithm(algorithmName: String): AIPlayer {
        when(algorithmName) {
//            "minimax" -> return MinimaxBasedAI();
            else -> throw IllegalArgumentException("unknown algorithm name: [$algorithmName]")
        }
        TODO()

    }

    fun getMovesFinder(movesFinderAlgorithm: String): MovesFinder {
        when(movesFinderAlgorithm) {
            "all_moves" -> return AllAvailableMovesFinder()
            "in_radius" -> return InRadiusMovesFinder(2)
            "shallow" -> return ShallowSearchMovesFinder(AllAvailableMovesFinder(), MoveEvaluator(WinnerFinder()), 10)
            else -> throw IllegalArgumentException("unknown algorithm to find moves")
        }

    }


}