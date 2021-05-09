package by.xxx.pupil.converter;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameRequest;
import org.springframework.stereotype.Component;

import static by.xxx.pupil.model.PlayerUtilsKt.resolveCellTypeFromSymbol;

@Component
public class RequestConverter {

    public Board toBoard(GameRequest request) {
        char[][] boardCells = request.getBoard();
        Board board = new Board(boardCells.length, boardCells[0].length);
        for (int i = 0; i < boardCells.length; i++) {
            for (int j = 0; j < boardCells[i].length; j++) {
                board.updateCellValue(i, j, resolveCellTypeFromSymbol(boardCells[i][j]));
            }
        }

        return board;
    }

}
