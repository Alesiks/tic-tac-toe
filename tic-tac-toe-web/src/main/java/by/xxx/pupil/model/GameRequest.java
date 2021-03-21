package by.xxx.pupil.model;

import java.util.Arrays;
import java.util.Objects;

public class GameRequest {

    private final CellType[][] board;
    private final Cell lastPlayerMove;

    public GameRequest(CellType[][] board, Cell lastPlayerMove) {
        this.board = board;
        this.lastPlayerMove = lastPlayerMove;
    }

    public CellType[][] getBoard() {
        return board;
    }

    public Cell getLatestPlayerMove() {
        return lastPlayerMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRequest that = (GameRequest) o;
        return Arrays.equals(board, that.board) &&
                Objects.equals(lastPlayerMove, that.lastPlayerMove);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(lastPlayerMove);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

}
