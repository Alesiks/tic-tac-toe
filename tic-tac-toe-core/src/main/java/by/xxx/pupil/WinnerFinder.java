package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static by.xxx.pupil.Constants.DEFAULT_WIN_SEQUENCE_LENGTH;
import static java.util.stream.Collectors.toList;

public class WinnerFinder {

    public boolean isMoveLeadToWin(Board board, Move move) {
        return isWinHorizontally(board, move)
                || isWinVertically(board, move)
                || isWinLeftToRightDiagonally(board, move)
                || isWinRightToLeftDiagonally(board, move);
    }

    private boolean isWinHorizontally(Board board, Move move) {
        int startJ = Math.max(move.getJ() - 4, 0);
        int endJ = Math.min(move.getJ() + 4, board.getWidth() - 1);

        List<CellType> possibleWinSequence = IntStream.rangeClosed(startJ, endJ)
                .mapToObj(j -> board.getCellValue(move.getI(), j))
                .collect(toList());

        return isSequenceContainWinCombination(possibleWinSequence);
    }

    private boolean isWinVertically(Board board, Move move) {
        int startI = Math.max(move.getI() - 4, 0);
        int endI = Math.min(move.getI() + 4, board.getHeight() - 1);

        List<CellType> possibleWinSequence = IntStream.rangeClosed(startI, endI)
                .mapToObj(i -> board.getCellValue(i, move.getJ()))
                .collect(toList());

        return isSequenceContainWinCombination(possibleWinSequence);
    }

    private boolean isWinLeftToRightDiagonally(Board board, Move move) {
        int minDelta = move.getI() - 4 >= 0 && move.getJ() - 4 >= 0
                ? 4
                : Math.min(move.getI(), move.getJ());

        int maxDelta = move.getI() + 4 < board.getHeight() && move.getJ() + 4 < board.getWidth()
                ? 4
                : Math.min(board.getHeight() - 1 - move.getI(), board.getWidth() - 1 - move.getJ());

        int startI = move.getI() - minDelta;
        int endI = move.getI() + maxDelta;
        int startJ = move.getJ() - minDelta;
        int endJ = move.getJ() + maxDelta;

        List<CellType> possibleWinSequence = new ArrayList<>();
        for (int i = startI, j = startJ; i <= endI && j <= endJ; i++, j++) {
            possibleWinSequence.add(board.getCellValue(i, j));
        }

        return isSequenceContainWinCombination(possibleWinSequence);
    }

    private boolean isWinRightToLeftDiagonally(Board board, Move move) {
        int delta = move.getI() + 4 < board.getHeight() && move.getJ() - 4 >= 0
                ? 4
                : Math.min(board.getHeight() - 1  - move.getI(), move.getJ());

        int endI = move.getI() + delta;
        int endJ = move.getJ() - delta;

        delta = move.getI() - 4 >= 0 && move.getJ() + 4 < board.getWidth()
                ? 4
                : Math.min(move.getI(), board.getWidth() - 1 - move.getJ());

        int startI = move.getI() - delta;
        int startJ = move.getJ() + delta;

        List<CellType> possibleWinSequence = new ArrayList<>();
        for (int i = startI, j = startJ; i <= endI && j >= endJ; i++, j--) {
            possibleWinSequence.add(board.getCellValue(i, j));
        }

        return isSequenceContainWinCombination(possibleWinSequence);
    }

    private boolean isSequenceContainWinCombination(List<CellType> possibleWinSequence) {
        int sequenceLength = 1;

        for (int i = 0; i < possibleWinSequence.size() - 1; i++) {
            if (possibleWinSequence.get(i) == possibleWinSequence.get(i + 1)
                    && CellType.EMPTY != possibleWinSequence.get(i)) {
                sequenceLength++;
                if (sequenceLength == DEFAULT_WIN_SEQUENCE_LENGTH) {
                    return true;
                }
            } else {
                sequenceLength = 1;
            }
        }

        return false;
    }

}
