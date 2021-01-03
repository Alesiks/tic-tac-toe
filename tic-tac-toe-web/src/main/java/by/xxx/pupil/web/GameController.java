package by.xxx.pupil.web;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.converter.RequestConverter;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Cell;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Request;
import by.xxx.pupil.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private AIPlayer aiPlayer;

    @Autowired
    private WinnerFinder winnerFinder;

    @Autowired
    private RequestConverter requestConverter;

    @PostMapping("/play")
    public Response play(@RequestBody Request request) {
        Board board = requestConverter.convert(request);
        Move playerMove = new Move(request.getLatestPlayerMove().getY(), request.getLatestPlayerMove().getX());

        if (winnerFinder.isMoveLeadToWin(board, playerMove)) {
            return new Response(GameState.CROSS_WIN, null);
        } else {
            Move aiMove = aiPlayer.nextMove(board);
            if(winnerFinder.isMoveLeadToWin(board, aiMove)) {
                return new Response(GameState.NOUGHT_WIN, new Cell(aiMove.getI(), aiMove.getJ(), CellType.NOUGHT));
            } else {
                return new Response(GameState.GAME_CONTINUES, new Cell(aiMove.getI(), aiMove.getJ(), CellType.NOUGHT));
            }
        }
    }

}
