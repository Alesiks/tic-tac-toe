package by.xxx.pupil

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.CellType

object BoardUtils {
    @JvmStatic
    fun isCellEmpty(board: Board, i: Int, j: Int): Boolean {
        return CellType.EMPTY === board.getCellValue(i, j)
    }
}