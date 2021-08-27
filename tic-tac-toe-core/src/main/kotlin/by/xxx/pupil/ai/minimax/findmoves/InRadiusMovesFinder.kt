package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import by.xxx.pupil.model.isCellEmpty


class InRadiusMovesFinder(private val availabilityRadius: Int) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val visited: MutableSet<Move> = HashSet()
        val availableMoves: MutableList<Move> = ArrayList()
        for (i in 0 until board.height) {
            for (j in 0 until board.width) {
                if (board.getCellValue(i, j) === CellType.CROSS || board.getCellValue(i, j) === CellType.NOUGHT) {
                    val allMoves = generateAllMoves(i, j, player)
                    allMoves.stream()
                            .filter { m: Move -> isMovePossible(m, board) }
                            .forEach { m: Move -> addMove(m, visited, availableMoves) }
                }
            }
        }
        return availableMoves
    }

    private fun generateAllMoves(y: Int, x: Int, player: Player): List<Move> {
        val allMoves: MutableList<Move> = ArrayList()
        for (i in 0..availabilityRadius) {
            for (j in 0..availabilityRadius) {
                if (i != 0 || j != 0) {
                    allMoves.add(Move(y + i, x + j, player))
                    allMoves.add(Move(y + i, x - j, player))
                    allMoves.add(Move(y - i, x + j, player))
                    allMoves.add(Move(y - i, x - j, player))
                }
            }
        }
        return allMoves
    }

    private fun isMovePossible(move: Move, board: Board): Boolean {
        return move.y >= 0 && move.x >= 0 && move.y < board.height && move.x < board.width && isCellEmpty(board, move.y, move.x)
    }

    private fun addMove(move: Move, visited: MutableSet<Move>, availableMoves: MutableList<Move>) {
        if (!visited.contains(move)) {
            visited.add(move)
            availableMoves.add(move)
        }
    }

}