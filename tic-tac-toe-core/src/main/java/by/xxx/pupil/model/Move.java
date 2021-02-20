package by.xxx.pupil.model;

import java.util.Objects;

public class Move {

    private final int i;
    private final int j;
    private final boolean isPerson;

    public Move(int i, int j, boolean isPerson) {
        this.i = i;
        this.j = j;
        this.isPerson = isPerson;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isPerson() {
        return isPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return i == move.i &&
                j == move.j &&
                isPerson == move.isPerson;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, isPerson);
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + i +
                ", j=" + j +
                ", isPerson=" + isPerson +
                '}';
    }
}
