package by.xxx.pupil.model;

import java.util.Objects;

public class WebCell {

    private final int y;
    private final int x;

    public WebCell(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebCell webCell = (WebCell) o;
        return y == webCell.y &&
                x == webCell.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }

}
