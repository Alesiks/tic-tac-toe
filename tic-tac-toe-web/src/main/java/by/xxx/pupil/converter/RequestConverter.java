package by.xxx.pupil.converter;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestConverter {

    public Board convert(Request request) {
        Board board = new Board(request.getHeight(), request.getWidth(), request.getWinSequenceLength());
        request.getBoard().forEach(cell -> board.updateCellToPossibleValue(cell.getY(), cell.getX(), cell.getCellType()));
        return board;
    }

}
