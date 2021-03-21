package by.xxx.pupil.web;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.converter.RequestConverter;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Cell;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameRequest;
import by.xxx.pupil.model.GameResponse;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/hi")
    public String play() {
        return "Hello world";
    }

    @PostMapping("/play")
    public GameResponse play(@RequestBody GameRequest request) {
        Board board = requestConverter.toBoard(request);
        Move playerMove = new Move(request.getLatestPlayerMove().getY() - 1, request.getLatestPlayerMove().getX() - 1, Player.CROSSES);

        if (winnerFinder.isMoveLeadToWin(board, playerMove)) {
            return new GameResponse(GameState.CROSS_WIN, request.getBoard(), null);
        } else {
            Move aiMove = aiPlayer.nextMove(board);
            CellType[][] boardCells = request.getBoard();
            boardCells[aiMove.getI()][aiMove.getJ()] = CellType.NOUGHT;
            if (winnerFinder.isMoveLeadToWin(board, aiMove)) {
                return new GameResponse(GameState.NOUGHT_WIN, boardCells, new Cell(aiMove.getI() + 1, aiMove.getJ() + 1));
            } else {
                return new GameResponse(GameState.GAME_CONTINUES, boardCells, new Cell(aiMove.getI() + 1, aiMove.getJ() + 1));
            }
        }
    }

}
