package by.xxx.pupil.model;

import java.util.Objects;

public class Move {

    private final int i;
    private final int j;

    public Move(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return i == move.i &&
                j == move.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
