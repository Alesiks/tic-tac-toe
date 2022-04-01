package by.pupil;

import by.pupil.model.Board;
import by.pupil.model.CellType;
import by.pupil.model.Move;
import by.pupil.model.Player;
import by.pupil.winning.WinnerFinder;
import org.junit.jupiter.api.Test;

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

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(5, 5, Player.NOUGHTS));

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

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(5, 3, Player.CROSSES));

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

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(4, 4, Player.NOUGHTS));

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

        boolean result = winnerFinder.isMoveLeadToWin(board, new Move(2, 2, Player.CROSSES));

        assertTrue(result);
    }

}
