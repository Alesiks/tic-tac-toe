package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;

public class BoardUtils {

    public static boolean isCellValueEmpty(Board board, int i, int j) {
        return CellType.EMPTY == board.getCellValue(i, j);
    }

}
