package by.xxx.pupil;

import org.apache.commons.lang3.Validate;

public class GameStrategy {

    public GameResult gameResult(Board board) {
        Validate.notNull(board, "board is null");

        GameResult gameResult = checkWinnerHorizontalLines(board);
        if(gameResult == GameResult.GAME_CONTINUES) {
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
