package by.pupil.web;

import by.pupil.ai.AIPlayer;
import by.pupil.converter.RequestConverter;
import by.pupil.winning.WinnerFinder;
import by.pupil.ai.AIPlayer;
import by.pupil.converter.RequestConverter;
import by.pupil.model.Board;
import by.pupil.model.CellType;
import by.pupil.model.GameRequest;
import by.pupil.model.GameResponse;
import by.pupil.model.GameStatus;
import by.pupil.model.Move;
import by.pupil.model.Player;
import by.pupil.model.WebCell;
import by.pupil.winning.WinnerFinder;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api")
public class GameController {

    @Autowired
    private AIPlayer aiPlayer;

    @Autowired
    private WinnerFinder winnerFinder;

    @Autowired
    private RequestConverter requestConverter;

//    @PostMapping("/play")
//    public GameResponse play(@RequestBody GameRequest request) {
//        Board board = requestConverter.toBoard(request);
//        Move playerMove = new Move(request.getPlayerMove().getY(), request.getPlayerMove().getX(), Player.CROSSES);
//
//        if (winnerFinder.isMoveLeadToWin(board, playerMove)) {
//            List<WebCell> winningSequence = winnerFinder.getWinSequenceForMove(board, playerMove)
//                    .stream()
//                    .map(cell -> new WebCell(cell.getY(), cell.getX()))
//                    .collect(Collectors.toList());
//            return new GameResponse(GameStatus.CROSS_WIN, request.getBoard(), null, winningSequence);
//        } else {
//            Move aiMove = aiPlayer.nextMove(board, Player.NOUGHTS, requestConverter.toGameProperties(request));
//            char[][] boardCells = request.getBoard();
//            boardCells[aiMove.getY()][aiMove.getX()] = CellType.NOUGHT.getSymbol();
//            if (winnerFinder.isMoveLeadToWin(board, aiMove)) {
//                List<WebCell> winningSequence = winnerFinder.getWinSequenceForMove(board, aiMove).stream()
//                        .map(cell -> new WebCell(cell.getY(), cell.getX()))
//                        .collect(Collectors.toList());
//                return new GameResponse(
//                        GameStatus.NOUGHT_WIN,
//                        boardCells,
//                        new WebCell(aiMove.getY(), aiMove.getX()),
//                        winningSequence
//                );
//            } else {
//                return new GameResponse(
//                        GameStatus.GAME_CONTINUES,
//                        boardCells,
//                        new WebCell(aiMove.getY(), aiMove.getX()),
//                        null
//                );
//            }
//        }
//    }

}
