package by.xxx.pupil.model

object PlayerUtils {
    @JvmStatic
    fun getRival(player: Player): Player {
        return when (player) {
            Player.CROSSES -> Player.NOUGHTS
            Player.NOUGHTS -> Player.CROSSES
        }
    }

    @JvmStatic
    fun getCorrespondingCellType(player: Player): CellType {
        return when (player) {
            Player.CROSSES -> CellType.CROSS
            Player.NOUGHTS -> CellType.NOUGHT
        }
    }
}