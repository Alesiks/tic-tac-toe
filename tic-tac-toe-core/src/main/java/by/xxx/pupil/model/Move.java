package by.xxx.pupil.model;

import java.util.Objects;

public class Move {

    private final int i;
    private final int j;
    private final Player player;

    public Move(int i, int j, Player player) {
        this.i = i;
        this.j = j;
        this.player = player;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return i == move.i &&
                j == move.j &&
                player == move.player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, player);
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + i +
                ", j=" + j +
                ", player=" + player +
                '}';
    }
}
