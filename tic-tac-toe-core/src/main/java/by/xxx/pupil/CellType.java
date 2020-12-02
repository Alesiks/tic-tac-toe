package by.xxx.pupil;

public enum CellType {

    CROSS('x'),
    NOUGHT('0'),
    EMPTY(' ');

    private char symbol;

    CellType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

}
