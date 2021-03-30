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