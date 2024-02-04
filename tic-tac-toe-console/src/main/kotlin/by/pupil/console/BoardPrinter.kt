package by.pupil.console

import by.pupil.model.Board
import by.pupil.model.CellType
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.stream.Collectors
import java.util.stream.IntStream

class BoardPrinter {
    var bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    fun print(board: Board) {
        try {
            val boardAsStrings = prepareForPrinting(board)
            for (boardLine in boardAsStrings) {
                bufferedWriter.write(boardLine)
                bufferedWriter.newLine()
            }
            bufferedWriter.newLine()
            bufferedWriter.flush()
        } catch (e: Exception) {
            throw RuntimeException("something wrong!")
        }
    }

    fun prepareForPrinting(board: Board): List<String> {
        val boardAsString: MutableList<String> = ArrayList()
        val width = board.width
        val height = board.height
        val firstLine =
            IntStream.iterate(1) { i: Int -> i + 1 }
                .limit(width.toLong())
                .mapToObj { i: Int -> if (i >= 10) i.toString() else "$i " }
                .collect(Collectors.joining("|", "   ", ""))
        boardAsString.add(firstLine)
        for (i in 0 until height) {
            val prefix = if (i >= 9) (i + 1).toString() else (i + 1).toString() + " "
            val line =
                IntStream.iterate(0) { j: Int -> j + 1 }
                    .limit(width.toLong())
                    .mapToObj { j: Int -> board.getCellValue(i, j) }
                    .map { cell: CellType -> cell.symbol.toString() + " " }
                    .collect(Collectors.joining("|", "$prefix ", ""))
            boardAsString.add(line)
        }
        return boardAsString
    }
}
