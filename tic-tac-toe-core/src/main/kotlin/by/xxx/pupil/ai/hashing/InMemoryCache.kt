package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.Board
import org.apache.logging.log4j.LogManager

class InMemoryCache(private val zobristHashing: ZobristHashing) : ScoreCache {

    private val logger = LogManager.getLogger(InMemoryCache::class.java)

    private val scoreCache: MutableMap<Long, Int> = HashMap()
    override fun getScore(board: Board): Int? {
        val hash = zobristHashing.hash(board)
        if (scoreCache.containsKey(hash)) {
//            logger.info("cache hit");
        } else {
//            logger.info("missed in cache");
        }
        return scoreCache[hash]
    }

    override fun getScore(board: Board?, boardHash: Long): Int {
//        scoreCache.computeIfAbsent(boardHash, board -> zobristHashing.hash(board));
        return 0
    }

    override fun putScore(board: Board, score: Int) {
        val hash = zobristHashing.hash(board)
        scoreCache[hash] = score
    }

    override fun putScore(boardHash: Long, score: Int) {
        scoreCache[boardHash] = score
    }

}