package by.xxx.pupil;

public enum Cell {

    CROSS('x'),
    NOUGHT('0'),
    EMPTY(' ');

    private char symbol;

    Cell(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

}
