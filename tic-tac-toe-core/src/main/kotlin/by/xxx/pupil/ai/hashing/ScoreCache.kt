package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.HashedBoard

interface ScoreCache {

    fun getScore(board: HashedBoard): Int?
    fun putScore(board: HashedBoard, score: Int)

}