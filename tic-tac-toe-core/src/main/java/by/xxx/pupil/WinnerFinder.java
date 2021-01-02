package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameResult;
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

    // todo fix method
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

        return sequenceLength == DEFAULT_WIN_SEQUENCE_LENGTH;
    }

    public GameResult gameResult(Board board) {
        Validate.notNull(board, "board is null");

        GameResult gameResult = checkWinnerHorizontalLines(board);
        if (gameResult == GameResult.GAME_CONTINUES) {
            gameResult = checkWinnerVerticalLines(board);
        }

        return gameResult;
    }

    private GameResult checkWinnerHorizontalLines(Board board) {
        GameResult gameResult = GameResult.GAME_CONTINUES;

        for (int i = 0; i < board.getHeight(); i++) {
            int j = 0;
            while (j + 1 < board.getWidth()) {
                int sequenceLength = 0;
                while (j + 1 < board.getWidth()
                        && board.getCellValue(i, j) == board.getCellValue(i, j + 1)
                        && board.getCellValue(i, j) != CellType.EMPTY) {
                    sequenceLength++;
                    j++;
                    if (sequenceLength == board.getWinSequenceLength() - 1) {
                        gameResult = getResultFromCell(board.getCellValue(i, j));
                        break;
                    }
                }
                j++;
                if (isGameEnded(gameResult)) {
                    break;
                }
            }
            if (isGameEnded(gameResult)) {
                break;
            }
        }

        return gameResult;
    }

    private GameResult checkWinnerVerticalLines(Board board) {
        GameResult gameResult = GameResult.GAME_CONTINUES;

        for (int j = 0; j < board.getWidth(); j++) {
            int i = 0;
            while (i + 1 < board.getHeight()) {
                int sequenceLength = 0;
                while (i + 1 < board.getHeight()
                        && board.getCellValue(i, j) == board.getCellValue(i + 1, j)
                        && board.getCellValue(i, j) != CellType.EMPTY) {
                    sequenceLength++;
                    i++;
                    if (sequenceLength == board.getWinSequenceLength() - 1) {
                        gameResult = getResultFromCell(board.getCellValue(i, j));
                        break;
                    }
                }
                i++;
                if (isGameEnded(gameResult)) {
                    break;
                }
            }
            if (isGameEnded(gameResult)) {
                break;
            }
        }

        return gameResult;
    }


    private boolean isGameEnded(GameResult gameResult) {
        return GameResult.NOUGHT_WIN == gameResult || GameResult.CROSS_WIN == gameResult;
    }

    private GameResult getResultFromCell(CellType cellType) {
        if (cellType == CellType.CROSS) {
            return GameResult.CROSS_WIN;
        } else if (cellType == CellType.NOUGHT) {
            return GameResult.NOUGHT_WIN;
        } else {
            return GameResult.GAME_CONTINUES;
        }
    }

}
