package by.pupil.model

fun isCellEmpty(board: Board, i: Int, j: Int): Boolean = CellType.EMPTY === board.getCellValue(i, j)

fun isCellBusyByPlayers(board: Board, i: Int, j: Int): Boolean =
        CellType.CROSS === board.getCellValue(i, j)
                || CellType.NOUGHT === board.getCellValue(i, j)


fun resolveCellTypeFromSymbol(symbol: Char): CellType = when (symbol) {
    'x' -> CellType.CROSS
    '0' -> CellType.NOUGHT
    ' ' -> CellType.EMPTY
    '#' -> CellType.OBSTACLE
    else -> throw RuntimeException("Unknown symbol $symbol for cell!")
}