package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.ai.minimax.findmoves.evaluate.MoveEvaluator
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import com.google.common.collect.MultimapBuilder
import java.util.stream.Collectors

class ShallowSearchMovesFinder(
        private val baseMovesFinder: MovesFinder,
        private val moveEvaluator: MoveEvaluator
) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = baseMovesFinder.getMoves(board, player)
        val scoreToMovesMap = MultimapBuilder.treeKeys().arrayListValues().build<Int, Move>()
        for (currMove in moves) {
            val currScore = moveEvaluator.evaluate(board, currMove)
            scoreToMovesMap.put(currScore, currMove)
        }
        return scoreToMovesMap.keySet()
                .stream()
                .flatMap { k: Int? -> scoreToMovesMap[k].stream() }
                .collect(Collectors.toList())
    }

}