package by.xxx.pupil;

public class BoardUtils {

    public static boolean isCellValueEmpty(Board board, int i, int j) {
        return Cell.EMPTY == board.getCellValue(i, j);
    }

}
