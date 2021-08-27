package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.Board

class NoCache : ScoreCache {

    override fun getScore(board: Board): Int? {
        return null
    }

    override fun getScore(board: Board?, boardHash: Long): Int {
        return 0
    }

    override fun putScore(board: Board, score: Int) {
    }

    override fun putScore(boardHash: Long, score: Int) {
    }

}