package by.pupil.winning

import by.pupil.Constants.WIN_SEQUENCE_LENGTH
import by.pupil.model.Board
import by.pupil.model.Cell
import by.pupil.model.CellType
import by.pupil.model.Move
import kotlin.math.max
import kotlin.math.min

class WinnerFinder {
    private val winSequenceShift = WIN_SEQUENCE_LENGTH - 1

    fun getWinSequenceForMove(
        board: Board,
        move: Move,
    ): List<Cell> {
        val findWinFunctionsList: MutableList<(Board, Move) -> List<Cell>> = ArrayList()
        findWinFunctionsList.add(this::findWinHorizontally)
        findWinFunctionsList.add(this::findWinVertically)
        findWinFunctionsList.add(this::findWinLeftToRightDiagonally)
        findWinFunctionsList.add(this::findWinRightToLeftDiagonally)

        for (f in findWinFunctionsList) {
            val winningSequence = f.invoke(board, move)
            if (winningSequence.isNotEmpty()) {
                return winningSequence
            }
        }

        return emptyList()
    }

    fun isMoveLeadToWin(
        board: Board,
        move: Move,
    ): Boolean {
        return findWinHorizontally(board, move).isNotEmpty() ||
            findWinVertically(board, move).isNotEmpty() ||
            findWinLeftToRightDiagonally(board, move).isNotEmpty() ||
            findWinRightToLeftDiagonally(board, move).isNotEmpty()
    }

    fun getDirections(
        board: Board,
        move: Move,
    ): List<List<Cell>> {
        val directionsList: MutableList<List<Cell>> = ArrayList()
        directionsList.add(getHorizontalDirection(board, move))
        directionsList.add(getVerticalDirection(board, move))
        directionsList.add(getLeftToRightDiagonalDirection(board, move))
        directionsList.add(getRightToLeftDiagonalDirection(board, move))

        return directionsList
    }

    private fun findWinHorizontally(
        board: Board,
        move: Move,
    ): List<Cell> {
        val possibleWinSequence = getHorizontalDirection(board, move)
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun getHorizontalDirection(
        board: Board,
        move: Move,
    ): List<Cell> {
        val row = move.y
        val column = move.x
        val columnsRange = max(column - winSequenceShift, 0)..min(column + winSequenceShift, board.width - 1)
        return columnsRange
            .map { j: Int -> Cell(row, j, board.getCellValue(row, j)) }
            .toList()
    }

    private fun findWinVertically(
        board: Board,
        move: Move,
    ): List<Cell> {
        val possibleWinSequence = getVerticalDirection(board, move)
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun getVerticalDirection(
        board: Board,
        move: Move,
    ): List<Cell> {
        val row = move.y
        val column = move.x
        val rowsRange = max(row - winSequenceShift, 0)..min(row + winSequenceShift, board.height - 1)
        return rowsRange
            .map { i: Int -> Cell(i, column, board.getCellValue(i, move.x)) }
            .toList()
    }

    private fun findWinLeftToRightDiagonally(
        board: Board,
        move: Move,
    ): List<Cell> {
        val possibleWinSequence = getLeftToRightDiagonalDirection(board, move)
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun getLeftToRightDiagonalDirection(
        board: Board,
        move: Move,
    ): List<Cell> {
        val minDelta =
            if (move.y - winSequenceShift >= 0 && move.x - winSequenceShift >= 0) {
                winSequenceShift
            } else {
                min(move.y, move.x)
            }
        val maxDelta =
            if (move.y + winSequenceShift < board.height && move.x + winSequenceShift < board.width) {
                winSequenceShift
            } else {
                min(board.height - 1 - move.y, board.width - 1 - move.x)
            }
        val startI = move.y - minDelta
        val endI = move.y + maxDelta
        val startJ = move.x - minDelta
        val endJ = move.x + maxDelta
        val diagonalDirection: MutableList<Cell> = ArrayList()
        var i = startI
        var j = startJ
        while (i <= endI && j <= endJ) {
            diagonalDirection.add(Cell(i, j, board.getCellValue(i, j)))
            i++
            j++
        }

        return diagonalDirection
    }

    private fun findWinRightToLeftDiagonally(
        board: Board,
        move: Move,
    ): List<Cell> {
        val possibleWinSequence = getRightToLeftDiagonalDirection(board, move)
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun getRightToLeftDiagonalDirection(
        board: Board,
        move: Move,
    ): List<Cell> {
        var delta =
            if (move.y + winSequenceShift < board.height && move.x - winSequenceShift >= 0) {
                winSequenceShift
            } else {
                min(board.height - 1 - move.y, move.x)
            }
        val endI = move.y + delta
        val endJ = move.x - delta
        delta =
            if (move.y - winSequenceShift >= 0 && move.x + winSequenceShift < board.width) {
                winSequenceShift
            } else {
                min(move.y, board.width - 1 - move.x)
            }
        val startI = move.y - delta
        val startJ = move.x + delta
        val diagonalDirecion: MutableList<Cell> = ArrayList()
        var i = startI
        var j = startJ
        while (i <= endI && j >= endJ) {
            diagonalDirecion.add(Cell(i, j, board.getCellValue(i, j)))
            i++
            j--
        }

        return diagonalDirecion
    }

    private fun getWinningSequenceIfExists(possibleWinSequence: List<Cell>): List<Cell> {
        var sequenceLength = 1
        for (i in 0 until possibleWinSequence.size - 1) {
            if (possibleWinSequence[i].cellType === possibleWinSequence[i + 1].cellType &&
                CellType.EMPTY !== possibleWinSequence[i].cellType &&
                CellType.OBSTACLE !== possibleWinSequence[i].cellType
            ) {
                sequenceLength++

                if (sequenceLength == WIN_SEQUENCE_LENGTH) {
                    val winningRange = (i + 1 downTo i + 1 - winSequenceShift)
                    return winningRange.map { index -> possibleWinSequence[index] }.toList()
                }
            } else {
                sequenceLength = 1
            }
        }
        return emptyList()
    }
}
