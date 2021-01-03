package by.xxx.pupil.model;

import java.util.Objects;

public class Cell {

    private final int y;
    private final int x;
    private final CellType cellType;

    public Cell(int y, int x, CellType cellType) {
        this.y = y;
        this.x = x;
        this.cellType = cellType;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public CellType getCellType() {
        return cellType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return y == cell.y &&
                x == cell.x &&
                cellType == cell.cellType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, cellType);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "y=" + y +
                ", x=" + x +
                ", cellType=" + cellType +
                '}';
    }
}
