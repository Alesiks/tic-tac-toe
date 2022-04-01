package by.pupil.ai.hashing

import by.pupil.model.HashedBoard

interface ScoreCache {

    fun getScore(board: HashedBoard): Int?
    fun putScore(board: HashedBoard, score: Int)

}