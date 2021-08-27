package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.Board

class InMemoryCache(private val zobristHashing: ZobristHashing) : ScoreCache {

    private val scoreCache: MutableMap<Long, Int> = HashMap()

    override fun getScore(board: Board): Int? {
        val hash = zobristHashing.hash(board)
        return scoreCache[hash]
    }

    override fun getScore(board: Board?, boardHash: Long): Int {
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