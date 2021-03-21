package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.CombinationsFinder
import by.xxx.pupil.GeneralCombination
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import org.apache.commons.lang3.Validate
import java.util.*

class ThreatMovesFinder(
        private val inRadiusMovesFinder: InRadiusMovesFinder,
        private val combinationsFinder: CombinationsFinder
) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = inRadiusMovesFinder.getMoves(board, player)
        val threatSpaceMoves: MutableList<Move> = ArrayList()
        for (move in moves) {
            board.updateCellValue(move.i, move.j, if (Player.CROSSES == player) CellType.CROSS else CellType.NOUGHT)
            val patternsList = combinationsFinder.getCombinations(board, move)
            if (patternsList.contains(GeneralCombination.STRAIGHT_FOUR) ||
                    patternsList.contains(GeneralCombination.FOUR) ||
                    patternsList.contains(GeneralCombination.THREE) ||
                    patternsList.contains(GeneralCombination.BROKEN_THREE)) {
                threatSpaceMoves.add(move)
            }
            board.updateCellValue(move.i, move.j, CellType.EMPTY)
        }
        return if (!threatSpaceMoves.isEmpty()) threatSpaceMoves else moves
    }

}