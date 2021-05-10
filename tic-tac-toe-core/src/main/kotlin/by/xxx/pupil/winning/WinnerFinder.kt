package by.xxx.pupil.winning

import by.xxx.pupil.Constants
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType
import by.xxx.pupil.model.Move
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.math.max
import kotlin.math.min

class WinnerFinder {
    fun isMoveLeadToWin(board: Board, move: Move): Boolean {
        return (isWinHorizontally(board, move)
                || isWinVertically(board, move)
                || isWinLeftToRightDiagonally(board, move)
                || isWinRightToLeftDiagonally(board, move))
    }

    private fun isWinHorizontally(board: Board, move: Move): Boolean {
        val startJ = max(move.j - 4, 0)
        val endJ = min(move.j + 4, board.width - 1)
        val possibleWinSequence = IntStream.rangeClosed(startJ, endJ)
                .mapToObj { j: Int -> board.getCellValue(move.i, j) }
                .collect(Collectors.toList())
        return isSequenceContainWinCombination(possibleWinSequence)
    }

    private fun isWinVertically(board: Board, move: Move): Boolean {
        val startI = max(move.i - 4, 0)
        val endI = min(move.i + 4, board.height - 1)
        val possibleWinSequence = IntStream.rangeClosed(startI, endI)
                .mapToObj { i: Int -> board.getCellValue(i, move.j) }
                .collect(Collectors.toList())
        return isSequenceContainWinCombination(possibleWinSequence)
    }

    private fun isWinLeftToRightDiagonally(board: Board, move: Move): Boolean {
        val minDelta = if (move.i - 4 >= 0 && move.j - 4 >= 0) 4 else min(move.i, move.j)
        val maxDelta = if (move.i + 4 < board.height && move.j + 4 < board.width) 4 else min(board.height - 1 - move.i, board.width - 1 - move.j)
        val startI = move.i - minDelta
        val endI = move.i + maxDelta
        val startJ = move.j - minDelta
        val endJ = move.j + maxDelta
        val possibleWinSequence: MutableList<CellType?> = ArrayList()
        var i = startI
        var j = startJ
        while (i <= endI && j <= endJ) {
            possibleWinSequence.add(board.getCellValue(i, j))
            i++
            j++
        }
        return isSequenceContainWinCombination(possibleWinSequence)
    }

    private fun isWinRightToLeftDiagonally(board: Board, move: Move): Boolean {
        var delta = if (move.i + 4 < board.height && move.j - 4 >= 0) 4 else min(board.height - 1 - move.i, move.j)
        val endI = move.i + delta
        val endJ = move.j - delta
        delta = if (move.i - 4 >= 0 && move.j + 4 < board.width) 4 else min(move.i, board.width - 1 - move.j)
        val startI = move.i - delta
        val startJ = move.j + delta
        val possibleWinSequence: MutableList<CellType?> = ArrayList()
        var i = startI
        var j = startJ
        while (i <= endI && j >= endJ) {
            possibleWinSequence.add(board.getCellValue(i, j))
            i++
            j--
        }
        return isSequenceContainWinCombination(possibleWinSequence)
    }

    private fun isSequenceContainWinCombination(possibleWinSequence: List<CellType?>): Boolean {
        var sequenceLength = 1
        for (i in 0 until possibleWinSequence.size - 1) {
            if (possibleWinSequence[i] === possibleWinSequence[i + 1]
                    && CellType.EMPTY !== possibleWinSequence[i]) {
                sequenceLength++
                if (sequenceLength == Constants.WIN_SEQUENCE_LENGTH) {
                    return true
                }
            } else {
                sequenceLength = 1
            }
        }
        return false
    }

}