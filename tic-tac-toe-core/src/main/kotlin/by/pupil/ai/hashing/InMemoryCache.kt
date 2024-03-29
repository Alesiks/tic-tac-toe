package by.pupil.ai.hashing

import by.pupil.model.HashedBoard

class InMemoryCache() : ScoreCache {
    private val scoreCache: MutableMap<Long, Int> = HashMap()

    override fun getScore(board: HashedBoard): Int? {
        return scoreCache[board.boardHash]
    }

    override fun putScore(
        board: HashedBoard,
        score: Int,
    ) {
        scoreCache[board.boardHash] = score
    }
}
