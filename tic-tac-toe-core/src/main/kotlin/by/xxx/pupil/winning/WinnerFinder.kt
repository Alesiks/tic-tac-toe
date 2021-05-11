package by.xxx.pupil.winning

import by.xxx.pupil.Constants.WIN_SEQUENCE_LENGTH
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Cell
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import kotlin.math.max
import kotlin.math.min

class WinnerFinder {

    private val WIN_SEQUENCE_SHIFT = WIN_SEQUENCE_LENGTH - 1;

    fun getWinSequenceForMove(board: Board, move: Move): List<Cell> {
        val findWinFunctionsList: MutableList<(Board, Move) -> List<Cell>> = ArrayList();
        findWinFunctionsList.add(this::findWinHorizontally)
        findWinFunctionsList.add(this::findWinVertically)
        findWinFunctionsList.add(this::findWinLeftToRightDiagonally)
        findWinFunctionsList.add(this::findWinRightToLeftDiagonally)

        for(f in findWinFunctionsList) {
            val winningSequence = f.invoke(board, move)
            if(winningSequence.isNotEmpty()) {
                return winningSequence;
            }
        }

        return emptyList();
    }

    fun isMoveLeadToWin(board: Board, move: Move): Boolean {
        return findWinHorizontally(board, move).isNotEmpty()
                || findWinVertically(board, move).isNotEmpty()
                || findWinLeftToRightDiagonally(board, move).isNotEmpty()
                || findWinRightToLeftDiagonally(board, move).isNotEmpty();
    }

    private fun findWinHorizontally(board: Board, move: Move): List<Cell> {
        val row = move.y
        val column = move.x
        val columnsRange = max(column - WIN_SEQUENCE_SHIFT, 0)..min(column + WIN_SEQUENCE_SHIFT, board.width - 1)
        val possibleWinSequence = columnsRange
                .map { j: Int -> Cell(row, j, board.getCellValue(row, j)) }
                .toList()

        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun findWinVertically(board: Board, move: Move): List<Cell> {
        val row = move.y
        val column = move.x
        val rowsRange = max(row - WIN_SEQUENCE_SHIFT, 0)..min(row + WIN_SEQUENCE_SHIFT, board.height - 1)
        val possibleWinSequence = rowsRange
                .map { i: Int -> Cell(i, column, board.getCellValue(i, move.x)) }
                .toList();
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun findWinLeftToRightDiagonally(board: Board, move: Move): List<Cell> {
        val minDelta =
                if (move.y - WIN_SEQUENCE_SHIFT >= 0 && move.x - WIN_SEQUENCE_SHIFT >= 0)
                    WIN_SEQUENCE_SHIFT
                else
                    min(move.y, move.x)
        val maxDelta =
                if (move.y + WIN_SEQUENCE_SHIFT < board.height && move.x + WIN_SEQUENCE_SHIFT < board.width)
                    WIN_SEQUENCE_SHIFT
                else
                    min(board.height - 1 - move.y, board.width - 1 - move.x)
        val startI = move.y - minDelta
        val endI = move.y + maxDelta
        val startJ = move.x - minDelta
        val endJ = move.x + maxDelta
        val possibleWinSequence: MutableList<Cell> = ArrayList()
        var i = startI
        var j = startJ
        while (i <= endI && j <= endJ) {
            possibleWinSequence.add(Cell(i, j, board.getCellValue(i, j)))
            i++
            j++
        }
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun findWinRightToLeftDiagonally(board: Board, move: Move): List<Cell> {
        var delta =
                if (move.y + WIN_SEQUENCE_SHIFT < board.height && move.x - WIN_SEQUENCE_SHIFT >= 0)
                    WIN_SEQUENCE_SHIFT
                else
                    min(board.height - 1 - move.y, move.x)
        val endI = move.y + delta
        val endJ = move.x - delta
        delta =
                if (move.y - WIN_SEQUENCE_SHIFT >= 0 && move.x + WIN_SEQUENCE_SHIFT < board.width)
                    WIN_SEQUENCE_SHIFT
                else
                    min(move.y, board.width - 1 - move.x)
        val startI = move.y - delta
        val startJ = move.x + delta
        val possibleWinSequence: MutableList<Cell> = ArrayList()
        var i = startI
        var j = startJ
        while (i <= endI && j >= endJ) {
            possibleWinSequence.add(Cell(i, j, board.getCellValue(i, j)))
            i++
            j--
        }
        return getWinningSequenceIfExists(possibleWinSequence)
    }

    private fun getWinningSequenceIfExists(possibleWinSequence: List<Cell>): List<Cell> {
        var sequenceLength = 1
        for (i in 0 until possibleWinSequence.size - 1) {
            if (possibleWinSequence[i].cellType === possibleWinSequence[i + 1].cellType
                    && CellType.EMPTY !== possibleWinSequence[i].cellType
                    && CellType.OBSTACLE !== possibleWinSequence[i].cellType) {
                sequenceLength++

                if (sequenceLength == WIN_SEQUENCE_LENGTH) {
                    val winningRange = (i + 1 downTo i + 1 - WIN_SEQUENCE_SHIFT)
                    return winningRange.map { index -> possibleWinSequence[index] }.toList()
                }

            } else {
                sequenceLength = 1
            }
        }
        return emptyList()
    }

}
