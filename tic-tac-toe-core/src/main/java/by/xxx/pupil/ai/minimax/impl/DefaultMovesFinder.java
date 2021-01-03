package by.xxx.pupil.ai.minimax.impl;

import by.xxx.pupil.ai.minimax.PossibleMovesFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultMovesFinder implements PossibleMovesFinder {

    private static final int AVAILABILITY_RADIUS = 2;

    @Override
    public List<Move> getAvailableMoves(Board board) {
        Set<Move> visited = new HashSet<>();
        Deque<Move> movesBFSQueue = new ArrayDeque<>();

        List<Move> availableMoves = new ArrayList<>();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if(board.getCellValue(i, j) == CellType.CROSS || board.getCellValue(i, j) == CellType.NOUGHT) {
                    List<Move> allMoves = generateAllMoves(i, j);
                    allMoves.stream()
                            .filter(m -> isMovePossible(m, board))
                            .forEach(m -> addMove(m, visited, movesBFSQueue, availableMoves));
                }
            }
        }

        return availableMoves;
    }

    private List<Move> generateAllMoves(int y, int x) {
        List<Move> allMoves = new ArrayList<>();
        for (int i = 0; i <= AVAILABILITY_RADIUS; i++) {
            for (int j = 0; j <= AVAILABILITY_RADIUS; j++) {
                if (i != 0 || j != 0) {
                    allMoves.add(new Move(y + i, x + j));
                    allMoves.add(new Move(y + i, x - j));
                    allMoves.add(new Move(y - i, x + j));
                    allMoves.add(new Move(y - i, x - j));
                }
            }
        }
        return allMoves;
    }

    private boolean isMovePossible(Move move, Board board) {
        return move.getI() >= 0
                && move.getJ() >= 0
                && move.getI() < board.getHeight()
                && move.getJ() < board.getWidth()
                && board.getCellValue(move.getI(), move.getJ()) == CellType.EMPTY;
    }

    private void addMove(Move move, Set<Move> visited, Deque<Move> movesBFSQueue, List<Move> availableMoves) {
        if (!visited.contains(move)) {
            visited.add(move);
            availableMoves.add(move);
            movesBFSQueue.add(move);
        }
    }

}
