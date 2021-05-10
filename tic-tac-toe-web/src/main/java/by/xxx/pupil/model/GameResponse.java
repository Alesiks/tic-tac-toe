package by.xxx.pupil.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameResponse {

    private final GameStatus gameStatus;
    private final char[][] board;
    private final Cell aiMove;
    private final List<Cell> winningSequence;

    public GameResponse(GameStatus gameStatus, char[][] board, Cell aiMove, List<Cell> winningSequence) {
        this.gameStatus = gameStatus;
        this.board = board;
        this.aiMove = aiMove;
        this.winningSequence = winningSequence;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Cell getAiMove() {
        return aiMove;
    }

    public char[][] getBoard() {
        return board;
    }

    public List<Cell> getWinningSequence() {
        return winningSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResponse that = (GameResponse) o;
        return gameStatus == that.gameStatus &&
                Arrays.equals(board, that.board) &&
                Objects.equals(aiMove, that.aiMove) &&
                Objects.equals(winningSequence, that.winningSequence);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(gameStatus, aiMove, winningSequence);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "gameState=" + gameStatus +
                ", board=" + Arrays.toString(board) +
                ", aiMove=" + aiMove +
                ", winningSequence=" + winningSequence +
                '}';
    }

}
