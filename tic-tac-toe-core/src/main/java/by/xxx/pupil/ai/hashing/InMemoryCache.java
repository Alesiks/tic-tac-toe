package by.xxx.pupil.ai.hashing;

import by.xxx.pupil.model.Board;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCache implements ScoreCache {
    private Logger logger = LogManager.getLogger(InMemoryCache.class);

    private final ZobristHashing zobristHashing;

    private Map<Long, Integer> scoreCache = new HashMap<>();

    public InMemoryCache(ZobristHashing zobristHashing) {
        Validate.notNull(zobristHashing, "zobristHashing is null");

        this.zobristHashing = zobristHashing;
    }

    @Override
    public Integer getScore(Board board) {
        long hash = zobristHashing.hash(board);
        if(scoreCache.containsKey(hash)) {
//            logger.info("cache hit");
        } else {
//            logger.info("missed in cache");
        }
        return scoreCache.get(hash);
    }

    @Override
    public Integer getScore(Board board, long boardHash) {
//        scoreCache.computeIfAbsent(boardHash, board -> zobristHashing.hash(board));


        return 0;
    }

    @Override
    public void putScore(Board board, int score) {
        long hash = zobristHashing.hash(board);
        scoreCache.put(hash, score);
    }

    @Override
    public void putScore(long boardHash, int score) {
        scoreCache.put(boardHash, score);
    }
}
