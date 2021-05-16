package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player
import kotlin.math.abs
import kotlin.random.Random

class ZobristHashing(height: Int, width: Int) {

    private val transpositionTable: Array<Array<LongArray>>

    companion object {
        private const val NUMBER_OF_PLAYERS = 2
    }

    init {
        transpositionTable = Array(height) { Array(width) { LongArray(NUMBER_OF_PLAYERS) } }
        for (i in 0 until height) {
            for (j in 0 until width) {
                for (p in 0 until NUMBER_OF_PLAYERS) {
                    transpositionTable[i][j][p] = abs(Random.nextLong())
                }
            }
        }
    }

    fun hash(board: Board): Long {
        var hash: Long = 0
        for (i in 0 until board.height) {
            for (j in 0 until board.width) {
                when (board.getCellValue(i, j)) {
                    CellType.CROSS -> hash = hash xor transpositionTable[i][j][0]
                    CellType.NOUGHT -> hash = hash xor transpositionTable[i][j][1]
                    else -> { }
                }
            }
        }
        return hash
    }

    fun updateHash(hash: Long, move: Move): Long {
        return if (Player.CROSSES == move.player) {
            hash xor transpositionTable[move.y][move.x][0]
        } else {
            hash xor transpositionTable[move.y][move.x][1]
        }
    }

}