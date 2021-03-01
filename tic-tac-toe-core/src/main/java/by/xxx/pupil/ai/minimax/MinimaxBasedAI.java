package by.xxx.pupil.ai.minimax;


import by.xxx.pupil.Constants;
import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.ai.hashing.ZobristHashing;
import by.xxx.pupil.ai.minimax.evaluate.Evaluator;
import by.xxx.pupil.ai.minimax.findmoves.MovesFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static by.xxx.pupil.model.PlayerUtils.getCorrespondingCellType;
import static by.xxx.pupil.model.PlayerUtils.getRival;

public class MinimaxBasedAI implements AIPlayer {
    private final Logger logger = LogManager.getLogger(MinimaxBasedAI.class);

    private final MovesFinder movesFinder;
    private final WinnerFinder winnerFinder;
    private final Evaluator evaluator;
    private final Minimax minimax;
    private final ZobristHashing zobristHashing;

    public MinimaxBasedAI(
            MovesFinder movesFinder,
            Evaluator evaluator,
            WinnerFinder winnerFinder,
            Minimax minimax,
            ZobristHashing zobristHashing
    ) {
        Validate.notNull(movesFinder, "null movesFinder");
        Validate.notNull(evaluator, "null evaluator");
        Validate.notNull(winnerFinder, "null winnerFinder");
        Validate.notNull(minimax, "null minimax");

        this.movesFinder = movesFinder;
        this.evaluator = evaluator;
        this.winnerFinder = winnerFinder;
        this.minimax = minimax;
        this.zobristHashing = zobristHashing;
    }

    @Override
    public Move nextMove(Board board, Player player) {
        List<Move> bestMoves = new ArrayList<>();
        int bestValue = Integer.MIN_VALUE;

        List<Move> possibleMoves = movesFinder.getMoves(board, player);
        Collections.reverse(possibleMoves);
        long hash = zobristHashing.hash(board);
        for (Move currentMove : possibleMoves) {
            board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), getCorrespondingCellType(player));
            int value;
//                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                return currentMove;
            } else {
                long newHash = zobristHashing.updateHash(hash, currentMove);
                value = minimax.minimax(board, 0, false, Constants.LOSE_STRATEGY_SCORE, Constants.WIN_STRATEGY_SCORE, getRival(player), newHash);
//                logger.debug("score for move[{},{}]: [{}]", currentMove.getI(), currentMove.getJ(), value);
            }

            board.updateCellValue(currentMove.getI(), currentMove.getJ(), CellType.EMPTY);
            if (value > bestValue) {
                bestMoves.clear();
                bestMoves.add(currentMove);
                bestValue = value;
            } else if (value == bestValue) {
                bestMoves.add(currentMove);
            }

        }

        return bestMoves.get(new Random().nextInt(bestMoves.size()));
    }

}
