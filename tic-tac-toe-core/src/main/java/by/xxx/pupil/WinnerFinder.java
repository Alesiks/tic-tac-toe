package by.xxx.pupil;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameResult;
import by.xxx.pupil.model.Move;
import org.apache.commons.lang3.Validate;

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

        int j = startJ;
        while (j + 1 <= endJ) {
            int sequenceLength = 0;
            while (j + 1 <= endJ
                    && board.getCellValue(move.getI(), j) == board.getCellValue(move.getI(), j + 1)
                    && board.getCellValue(move.getI(), j) != CellType.EMPTY) {
                sequenceLength++;
                j++;
                if (sequenceLength == board.getWinSequenceLength() - 1) {
                    return true;
                }
            }
            j++;
        }
        return false;
    }

    private boolean isWinVertically(Board board, Move move) {
        int startI = Math.max(move.getI() - 4, 0);
        int endI = Math.min(move.getI() + 4, board.getHeight() - 1);

        int i = startI;
        while (i + 1 <= endI) {
            int sequenceLength = 0;
            while (i + 1 <= endI
                    && board.getCellValue(i, move.getJ()) == board.getCellValue(i + 1, move.getJ())
                    && board.getCellValue(i, move.getJ()) != CellType.EMPTY) {
                sequenceLength++;
                i++;
                if (sequenceLength == board.getWinSequenceLength() - 1) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    private boolean isWinLeftToRightDiagonally(Board board, Move move) {
        int startI = Math.max(move.getI() - 4, 0);
        int endI = Math.min(move.getI() + 4, board.getHeight() - 1);
        int startJ = Math.max(move.getJ() - 4, 0);
        int endJ = Math.min(move.getJ() + 4, board.getWidth() - 1);


        int i = startI;
        int j = startJ;
        while (i + 1 <= endI && j + 1 <= endJ) {
            int sequenceLength = 0;
            while (i + 1 <= endI && j + 1 <= endJ
                    && board.getCellValue(i, j) == board.getCellValue(i + 1, j + 1)
                    && board.getCellValue(i, j) != CellType.EMPTY) {
                sequenceLength++;
                i++; j++;
                if (sequenceLength == board.getWinSequenceLength() - 1) {
                    return true;
                }
            }
            i++;
            j++;
        }
        return false;
    }

    // fix method
    private boolean isWinRightToLeftDiagonally(Board board, Move move) {
        int startI = Math.max(move.getI() - 4, 0);
        int endJ = move.getJ() + (move.getI() - startI);

        int startJ = move.getJ() - (move.getI() - startI);
        int endI = move.getI() + (move.getJ() - startJ);

        int i = startI;
        int j = endJ;
        while (i + 1 <= endI && j - 1 >= startJ) {
            int sequenceLength = 0;
            while (i + 1 <= endI && j - 1 >= startJ
                    && board.getCellValue(i, j) == board.getCellValue(i + 1, j - 1)
                    && board.getCellValue(i, j) != CellType.EMPTY) {
                sequenceLength++;
                i++; j--;
                if (sequenceLength == board.getWinSequenceLength() - 1) {
                    return true;
                }
            }
            i++;
            j--;
        }
        return false;
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
