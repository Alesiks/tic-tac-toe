package by.xxx.pupil.ai.minimax.findmoves;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.apache.commons.lang3.Validate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.xxx.pupil.BoardUtils.isCellEmpty;

public class InRadiusMovesFinder implements MovesFinder {

    private final int availabilityRadius;

    public InRadiusMovesFinder(int availabilityRadius) {
        Validate.isTrue(availabilityRadius > 0, "availabilityRadius should be greater than 0");

        this.availabilityRadius = availabilityRadius;
    }

    @Override
    public List<Move> getMoves(Board board, Player player) {
        Set<Move> visited = new HashSet<>();
        Deque<Move> movesBFSQueue = new ArrayDeque<>();

        List<Move> availableMoves = new ArrayList<>();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCellValue(i, j) == CellType.CROSS || board.getCellValue(i, j) == CellType.NOUGHT) {
                    List<Move> allMoves = generateAllMoves(i, j, player);
                    allMoves.stream()
                            .filter(m -> isMovePossible(m, board))
                            .forEach(m -> addMove(m, visited, movesBFSQueue, availableMoves));
                }
            }
        }

        return availableMoves;
    }

    private List<Move> generateAllMoves(int y, int x, Player player) {
        List<Move> allMoves = new ArrayList<>();
        for (int i = 0; i <= availabilityRadius; i++) {
            for (int j = 0; j <= availabilityRadius; j++) {
                if (i != 0 || j != 0) {
                    allMoves.add(new Move(y + i, x + j, player));
                    allMoves.add(new Move(y + i, x - j, player));
                    allMoves.add(new Move(y - i, x + j, player));
                    allMoves.add(new Move(y - i, x - j, player));
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
                && isCellEmpty(board, move.getI(), move.getJ());
    }

    private void addMove(Move move, Set<Move> visited, Deque<Move> movesBFSQueue, List<Move> availableMoves) {
        if (!visited.contains(move)) {
            visited.add(move);
            availableMoves.add(move);
            movesBFSQueue.add(move);
        }
    }

}
