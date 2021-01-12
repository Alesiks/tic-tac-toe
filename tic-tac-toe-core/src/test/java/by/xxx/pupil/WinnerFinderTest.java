package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;
import org.junit.jupiter.api.Test;

import static by.xxx.pupil.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WinnerFinderTest {

    private final WinnerFinder winnerFinder = new WinnerFinder();

    @Test
    public void horizontalMoveLeadsToWinTest() {
        CellType[][] cellTypes = {
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.NOUGHT, CellType.NOUGHT, CellType.NOUGHT, CellType.NOUGHT, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
        };

        Board board = new Board(cellTypes);

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(5, 5));

        assertTrue(result);
    }

    @Test
    public void verticalMoveLeadsToWinTest() {
        CellType[][] cellTypes = {
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
        };

        Board board = new Board(cellTypes);

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(5, 3));

        assertTrue(result);
    }

    @Test
    public void leftToRightDiagonalMoveLeadsToWinTest() {
        CellType[][] cellTypes = {
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
        };

        Board board = new Board(cellTypes);

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(4, 4));

        assertTrue(result);
    }

    @Test
    public void rightToLeftDiagonalMoveLeadsToWinTest() {
        CellType[][] cellTypes = {
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.NOUGHT, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.NOUGHT, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.NOUGHT, CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.CROSS, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
                {CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY, CellType.EMPTY},
        };

        Board board = new Board(cellTypes);

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(2, 2));

        assertTrue(result);
    }

}
