package by.xxx.pupil.model;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<String> getAllLines() {
        List<String> allLines = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            allLines.add(getHorizontalLine(i));
            allLines.add(getLeftToRightDiagonalLine(i, 0));
            allLines.add(getRightToLeftDiagonalLine(i, 0));
            if(i > 1) {
                allLines.add(getRightToLeftDiagonalLine(i, width - 1));
            }
        }
        for (int j = 0; j < width; j++) {
            allLines.add(getVerticalLine(j));
            if(j > 0) {
                allLines.add(getLeftToRightDiagonalLine(0, j));
            }
        }

        return allLines;
    }


    public String getHorizontalLine(int line) {
        return IntStream.range(0, width)
                .mapToObj(j -> board[line][j])
                .map(CellType::getSymbol)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String getVerticalLine(int line) {
        return IntStream.range(0, height)
                .mapToObj(j -> board[j][line])
                .map(CellType::getSymbol)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String getLeftToRightDiagonalLine(int y, int x) {
        int delta = Math.min(x, y);
        int i = y - delta;
        int j = x - delta;

        StringBuilder diagonal = new StringBuilder();
        while (i < height && j < width) {
            diagonal.append(board[i][j].getSymbol());
            i++;
            j++;
        }
        return diagonal.toString();
    }

    public String getRightToLeftDiagonalLine(int y, int x) {
        int i, j;
        if (y < width - 1 - x) {
            int delta = y;
            i = 0;
            j = x + delta;
        } else {
            int delta = width - 1 - x;
            i = y - delta;
            j = width - 1;
        }

        StringBuilder diagonal = new StringBuilder();
        while (i < height && j >= 0) {
            if (i < 0) {
                i = i;
            }
            diagonal.append(board[i][j].getSymbol());
            i++;
            j--;
        }
        return diagonal.toString();
    }

}
