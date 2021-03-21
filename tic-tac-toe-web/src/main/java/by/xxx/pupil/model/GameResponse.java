package by.xxx.pupil.model;

import java.util.Arrays;
import java.util.Objects;

public class GameResponse {

    private final GameState gameState;
    private final CellType[][] board;
    private final Cell aiMove;

    public GameResponse(GameState gameState, CellType[][] board, Cell aiMove) {
        this.gameState = gameState;
        this.board = board;
        this.aiMove = aiMove;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Cell getAiMove() {
        return aiMove;
    }

    public CellType[][] getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResponse that = (GameResponse) o;
        return gameState == that.gameState &&
                Arrays.equals(board, that.board) &&
                Objects.equals(aiMove, that.aiMove);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(gameState, aiMove);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "gameState=" + gameState +
                ", board=" + Arrays.toString(board) +
                ", aiMove=" + aiMove +
                '}';
    }

}
