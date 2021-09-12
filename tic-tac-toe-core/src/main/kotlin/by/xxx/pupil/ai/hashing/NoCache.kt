package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.HashedBoard

class NoCache : ScoreCache {

    override fun getScore(board: HashedBoard): Int? {
        return null
    }

    override fun putScore(board: HashedBoard, score: Int) {
    }


}