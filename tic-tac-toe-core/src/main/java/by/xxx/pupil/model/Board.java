package by.xxx.pupil.model;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * height * width
 * 3*4
 * xxxx
 * xxxx
 * xxxx
 */

public class Board {
    private final Logger logger = LogManager.getLogger(Board.class);

    private final int width;
    private final int height;
    private final CellType[][] board;

    public Board(int height, int width) {
        Validate.isTrue(height > 0, "height is less or equal than zero");
        Validate.isTrue(width > 0, "width is less or equal than zero");

        this.height = height;
        this.width = width;

        this.board = new CellType[height][width];
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = new CellType[width];
            Arrays.fill(this.board[i], CellType.EMPTY);
        }
    }

    public Board(CellType[][] board) {
        Validate.notNull(board, "board is null");
        this.height = board.length;
        for (int i = 0; i < board.length - 1; i++) {
            Validate.isTrue(board[i].length == board[i + 1].length, "board is not rectangle");
        }
        this.width = board[0].length;
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellType getCellValue(int i, int j) {
        return board[i][j];
    }

    public void updateCellToPossibleValue(int i, int j, CellType cellType) {
        Validate.isTrue(i >= 0 && i < height, "i coordinate is less than 0 or greater than possible height");
        Validate.isTrue(j >= 0 && j < width, "j coordinate is less than 0 or greater than possible width");
        Validate.isTrue(cellType != null && CellType.EMPTY != cellType, "Cell is null or empty");

        if (board[i][j] != CellType.EMPTY) {
            logger.error("Cell [{}][{}] is not empty", i, j);
        } else {
            board[i][j] = cellType;
        }
    }

    public void updateCellValue(int i, int j, CellType cellType) {
        Validate.isTrue(i >= 0 && i < height, "i coordinate is less than 0 or greater than possible height");
        Validate.isTrue(j >= 0 && j < width, "j coordinate is less than 0 or greater than possible width");

        board[i][j] = cellType;
    }

}
