package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.ai.combinations.CombinationsFinder
import by.xxx.pupil.ai.combinations.GeneralCombination
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

class ThreatMovesFinder(
        private val inRadiusMovesFinder: InRadiusMovesFinder,
        private val combinationsFinder: CombinationsFinder
) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = inRadiusMovesFinder.getMoves(board, player)
        val threatSpaceMoves: MutableList<Move> = ArrayList()
        for (move in moves) {
            board.updateCellValue(move.y, move.x, if (Player.CROSSES == player) CellType.CROSS else CellType.NOUGHT)
            val patternsList = combinationsFinder.getCombinations(board, move)
            if (patternsList.contains(GeneralCombination.STRAIGHT_FOUR) ||
                    patternsList.contains(GeneralCombination.FOUR) ||
                    patternsList.contains(GeneralCombination.THREE) ||
                    patternsList.contains(GeneralCombination.BROKEN_THREE)) {
                threatSpaceMoves.add(move)
            }
            board.updateCellValue(move.y, move.x, CellType.EMPTY)
        }
        return if (!threatSpaceMoves.isEmpty()) threatSpaceMoves else moves
    }

}