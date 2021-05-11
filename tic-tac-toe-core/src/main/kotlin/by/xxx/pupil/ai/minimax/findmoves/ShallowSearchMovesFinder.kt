package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.ai.hashing.ZobristHashing
import by.xxx.pupil.ai.minimax.evaluate.Evaluator
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import com.google.common.collect.MultimapBuilder
import java.util.stream.Collectors

class ShallowSearchMovesFinder(
        private val baseMovesFinder: MovesFinder,
        private val evaluator: Evaluator,
        private val zobristHashing: ZobristHashing
) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = baseMovesFinder.getMoves(board, player)
        val boardHash = zobristHashing.hash(board)
        val scoreToMovesMap = MultimapBuilder.treeKeys().arrayListValues().build<Int, Move>()
        for (currMove in moves) {
            val hashForCurrMove = zobristHashing.updateHash(boardHash, currMove)
            board.updateCellValue(currMove.y, currMove.x, if (Player.CROSSES == currMove.player) CellType.CROSS else CellType.NOUGHT)
            val currScore = evaluator.evaluate(board, currMove, hashForCurrMove)
            scoreToMovesMap.put(currScore, currMove)
            board.updateCellValue(currMove.y, currMove.x, CellType.EMPTY)
        }
        return scoreToMovesMap.keySet()
                .stream()
                .flatMap { k: Int? -> scoreToMovesMap[k].stream() }
                .collect(Collectors.toList())
    }

}