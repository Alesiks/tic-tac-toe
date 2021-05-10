package by.xxx.pupil.ai.minimax.findmoves

import by.xxx.pupil.model.*
import java.util.*

class InRadiusMovesFinder(private val availabilityRadius: Int) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val visited: MutableSet<Move> = HashSet()
        val movesBFSQueue: Deque<Move> = ArrayDeque()
        val availableMoves: MutableList<Move> = ArrayList()
        for (i in 0 until board.height) {
            for (j in 0 until board.width) {
                if (board.getCellValue(i, j) === CellType.CROSS || board.getCellValue(i, j) === CellType.NOUGHT) {
                    val allMoves = generateAllMoves(i, j, player)
                    allMoves.stream()
                            .filter { m: Move -> isMovePossible(m, board) }
                            .forEach { m: Move -> addMove(m, visited, movesBFSQueue, availableMoves) }
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
        return move.i >= 0 && move.j >= 0 && move.i < board.height && move.j < board.width && isCellEmpty(board, move.i, move.j)
    }

    private fun addMove(move: Move, visited: MutableSet<Move>, movesBFSQueue: Deque<Move>, availableMoves: MutableList<Move>) {
        if (!visited.contains(move)) {
            visited.add(move)
            availableMoves.add(move)
            movesBFSQueue.add(move)
        }
    }

}