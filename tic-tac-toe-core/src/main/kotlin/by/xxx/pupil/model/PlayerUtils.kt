package by.xxx.pupil.model

fun getRival(player: Player): Player =
        when (player) {
            Player.CROSSES -> Player.NOUGHTS
            Player.NOUGHTS -> Player.CROSSES
        }

fun getCorrespondingCellType(player: Player): CellType =
        when (player) {
            Player.CROSSES -> CellType.CROSS
            Player.NOUGHTS -> CellType.NOUGHT
        }

fun resolveCellTypeFromSymbol(symbol: Char): CellType =
        when (symbol) {
            'x' -> CellType.CROSS
            '0' -> CellType.NOUGHT
            ' ' -> CellType.EMPTY
            '#' -> CellType.OBSTACLE
            else -> throw RuntimeException("Unknown symbol $symbol for cell!")
        }