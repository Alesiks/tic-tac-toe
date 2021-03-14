package by.xxx.pupil.ai.hashing

import by.xxx.pupil.model.Board

interface ScoreCache {
    fun getScore(board: Board): Int?
    fun getScore(board: Board?, boardHash: Long): Int
    fun putScore(board: Board, score: Int)
    fun putScore(boardHash: Long, score: Int)
}