package by.pupil.ai.minimax.findmoves

import by.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.pupil.model.Board
import by.pupil.model.Move
import by.pupil.model.Player
import com.google.common.collect.MultimapBuilder
import java.util.stream.Collectors
import kotlin.math.min

class ShallowSearchMovesFinder(
        private val baseMovesFinder: MovesFinder,
        private val moveEvaluator: MoveEvaluator,
        private val movesNumber: Int
) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = baseMovesFinder.getMoves(board, player)
        val scoreToMovesMap = MultimapBuilder.treeKeys().arrayListValues().build<Int, Move>()
        for (currMove in moves) {
            val currScore = moveEvaluator.evaluate(board, currMove)
            scoreToMovesMap.put(currScore, currMove)
        }
        val ratedMovesList = scoreToMovesMap.keySet()
                .stream()
                .flatMap { k: Int? -> scoreToMovesMap[k].stream() }
                .collect(Collectors.toList())
                .reversed()
        return ratedMovesList.subList(0, min(movesNumber, ratedMovesList.size))
    }

}