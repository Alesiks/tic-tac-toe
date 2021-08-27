package by.xxx.pupil.model

import org.apache.commons.lang3.Validate
import org.apache.logging.log4j.LogManager
import kotlin.math.min

/**
 * height * width
 * 3*4
 * xxxx
 * xxxx
 * xxxx
 */
class Board {
    private val logger = LogManager.getLogger(Board::class.java)

    val width: Int
    val height: Int
    private val board: Array<Array<CellType>>

    constructor(height: Int, width: Int) {
        Validate.isTrue(height > 0, "height [$height] should be greater than zero")
        Validate.isTrue(width > 0, "width [$width] should be greater than zero")

        this.height = height
        this.width = width

        board = Array(height) { Array(width) { CellType.EMPTY } }
    }

    constructor(board: Array<Array<CellType>>) {
        Validate.notNull(board, "board is null")
        height = board.size
        for (i in 0 until board.size - 1) {
            Validate.isTrue(board[i].size == board[i + 1].size, "board is not rectangle")
        }
        width = board[0].size
        this.board = board
    }

    fun getCellValue(i: Int, j: Int): CellType {
        return board[i][j]
    }

    fun updateCellToPossibleValue(i: Int, j: Int, cellType: CellType) {
        Validate.isTrue(i in 0 until height, "i coordinate is less than 0 or greater than possible height")
        Validate.isTrue(j in 0 until width, "j coordinate is less than 0 or greater than possible width")
        Validate.isTrue(CellType.EMPTY !== cellType, "Cell is null or empty")

        if (board[i][j] !== CellType.EMPTY) {
            logger.error("Cell [{}][{}] is not empty", i, j)
        } else {
            board[i][j] = cellType
        }
    }

    fun updateCellValue(i: Int, j: Int, cellType: CellType) {
        Validate.isTrue(i in 0 until height, "i coordinate is less than 0 or greater than possible height")
        Validate.isTrue(j in 0 until width, "j coordinate is less than 0 or greater than possible width")

        board[i][j] = cellType
    }

    val allLines: List<String>
        get() {
            val allLines: MutableList<String> = ArrayList()
            for (i in 0 until height) {
                allLines.add(getHorizontalLine(i))
                allLines.add(getLeftToRightDiagonalLine(i, 0))
                allLines.add(getRightToLeftDiagonalLine(i, 0))
                if (i > 1) {
                    allLines.add(getRightToLeftDiagonalLine(i, width - 1))
                }
            }
            for (j in 0 until width) {
                allLines.add(getVerticalLine(j))
                if (j > 0) {
                    allLines.add(getLeftToRightDiagonalLine(0, j))
                }
            }
            return allLines
        }

    fun getHorizontalLine(line: Int): String {
        return board[line].map { v -> v.symbol }.joinToString("");
    }

    fun getVerticalLine(line: Int): String {
        return (0 until height)
                .map { j -> board[j][line].symbol }
                .joinToString("");
    }

    fun getLeftToRightDiagonalLine(y: Int, x: Int): String {
        val delta = min(x, y)
        var i = y - delta
        var j = x - delta
        val diagonal = StringBuilder()
        while (i < height && j < width) {
            diagonal.append(board[i][j].symbol)
            i++
            j++
        }
        return diagonal.toString()
    }

    fun getRightToLeftDiagonalLine(y: Int, x: Int): String {
        var i: Int
        var j: Int
        if (y < width - 1 - x) {
            i = 0
            j = x + y
        } else {
            val delta = width - 1 - x
            i = y - delta
            j = width - 1
        }
        val diagonal = StringBuilder()
        while (i < height && j >= 0) {
            if (i < 0) {
                i = i
            }
            diagonal.append(board[i][j].symbol)
            i++
            j--
        }
        return diagonal.toString()
    }

    override fun toString(): String {
        var res = ""
        for (i in 0 until height) {
            res += board[i].map { cellType -> cellType.symbol }.toString()
            res += "\n"
        }
        return res
    }

}