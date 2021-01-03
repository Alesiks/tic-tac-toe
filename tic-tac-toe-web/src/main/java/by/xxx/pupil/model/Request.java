package by.xxx.pupil.model;

import java.util.List;
import java.util.Objects;

public class Request {

    private final int height;
    private final int width;
    private final int winSequenceLength;
    private final List<Cell> board;
    private final Cell latestPlayerMove;

    public Request(int height, int width, int winSequenceLength, List<Cell> board, Cell latestPlayerMove) {
        this.height = height;
        this.width = width;
        this.winSequenceLength = winSequenceLength;
        this.board = board;
        this.latestPlayerMove = latestPlayerMove;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getWinSequenceLength() {
        return winSequenceLength;
    }

    public List<Cell> getBoard() {
        return board;
    }

    public Cell getLatestPlayerMove() {
        return latestPlayerMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return height == request.height &&
                width == request.width &&
                winSequenceLength == request.winSequenceLength &&
                Objects.equals(board, request.board) &&
                Objects.equals(latestPlayerMove, request.latestPlayerMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, winSequenceLength, board, latestPlayerMove);
    }

    @Override
    public String toString() {
        return "Request{" +
                "height=" + height +
                ", width=" + width +
                ", winSequenceLength=" + winSequenceLength +
                ", board=" + board +
                ", latestPlayerMove=" + latestPlayerMove +
                '}';
    }
}
