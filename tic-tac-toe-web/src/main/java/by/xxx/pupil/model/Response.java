package by.xxx.pupil.model;

import java.util.Objects;

public class Response {

    private final GameState gameState;
    private final Cell aiMove;

    public Response(GameState gameState, Cell aiMove) {
        this.gameState = gameState;
        this.aiMove = aiMove;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Cell getAiMove() {
        return aiMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return gameState == response.gameState &&
                Objects.equals(aiMove, response.aiMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameState, aiMove);
    }

    @Override
    public String toString() {
        return "Response{" +
                "gameState=" + gameState +
                ", aiMove=" + aiMove +
                '}';
    }
}
