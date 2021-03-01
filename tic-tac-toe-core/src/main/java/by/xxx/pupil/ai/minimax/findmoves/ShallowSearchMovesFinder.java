package by.xxx.pupil.ai.minimax.findmoves;

import by.xxx.pupil.ai.hashing.ZobristHashing;
import by.xxx.pupil.ai.minimax.evaluate.Evaluator;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;
import by.xxx.pupil.model.Player;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class ShallowSearchMovesFinder implements MovesFinder {

    private final MovesFinder baseMovesFinder;
    private final Evaluator evaluator;
    private final ZobristHashing zobristHashing;

    public ShallowSearchMovesFinder(MovesFinder baseMovesFinder, Evaluator evaluator, ZobristHashing zobristHashing) {
        this.baseMovesFinder = baseMovesFinder;
        this.evaluator = evaluator;
        this.zobristHashing = zobristHashing;
    }


    @Override
    public List<Move> getMoves(Board board, Player player) {
        List<Move> moves = baseMovesFinder.getMoves(board, player);

        long boardHash = zobristHashing.hash(board);

        ListMultimap<Integer, Move> scoreToMovesMap =
                MultimapBuilder.treeKeys().arrayListValues().build();

        for(Move currMove: moves) {
            long hashForCurrMove = zobristHashing.updateHash(boardHash, currMove);
            board.updateCellValue(currMove.getI(), currMove.getJ(), Player.CROSSES.equals(currMove.getPlayer()) ? CellType.CROSS : CellType.NOUGHT);
            int currScore = evaluator.evaluate(board, currMove, hashForCurrMove);
            scoreToMovesMap.put(currScore, currMove);
            board.updateCellValue(currMove.getI(), currMove.getJ(), CellType.EMPTY);
        }

        return scoreToMovesMap.keySet().stream().flatMap(k -> scoreToMovesMap.get(k).stream()).collect(Collectors.toList());
    }
}
