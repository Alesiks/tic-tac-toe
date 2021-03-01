package by.xxx.pupil.model;

public class PlayerUtils {

    public static Player getRival(Player player) {
        switch (player) {
            case CROSSES:
                return Player.NOUGHTS;
            case NOUGHTS:
                return Player.CROSSES;
            default:
                throw new RuntimeException("Unknown player!");
        }
    }

    public static CellType getCorrespondingCellType(Player player) {
        switch (player) {
            case CROSSES:
                return CellType.CROSS;
            case NOUGHTS:
                return CellType.NOUGHT;
            default:
                throw new RuntimeException("Unknown player!");
        }
    }

}
