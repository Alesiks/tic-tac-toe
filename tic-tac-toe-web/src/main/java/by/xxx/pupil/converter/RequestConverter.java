package by.xxx.pupil.converter;

import by.xxx.pupil.Constants;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.GameRequest;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static by.xxx.pupil.model.BoardUtilsKt.resolveCellTypeFromSymbol;

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

    public Map<String, Object> toGameProperties(GameRequest request) {
        Validate.isTrue(
                request.getDifficultyLevel() > 0 && request.getDifficultyLevel() < 10,
                "difficulty level is out of range"
        );
        int depth = request.getDifficultyLevel() != null ? request.getDifficultyLevel() : Constants.DEFAULT_MINIMAX_DEPTH_LIMIT;

        Map<String, Object> props = new HashMap<>();
        props.put(Constants.MINIMAX_DEPTH_PROPERTY, depth);

        return props;
    }

}
