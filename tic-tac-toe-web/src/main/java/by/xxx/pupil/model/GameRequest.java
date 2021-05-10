package by.xxx.pupil.model;

import java.util.Arrays;
import java.util.Objects;

public class GameRequest {

    private final char[][] board;
    private final Cell playerMove;
    private final Integer difficultyLevel;

    public GameRequest(char[][] board, Cell playerMove, Integer difficultyLevel) {
        this.board = board;
        this.playerMove = playerMove;
        this.difficultyLevel = difficultyLevel;
    }

    public char[][] getBoard() {
        return board;
    }

    public Cell getPlayerMove() {
        return playerMove;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRequest that = (GameRequest) o;
        return difficultyLevel.equals(that.difficultyLevel) &&
                Arrays.equals(board, that.board) &&
                Objects.equals(playerMove, that.playerMove);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(playerMove, difficultyLevel);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }
}
