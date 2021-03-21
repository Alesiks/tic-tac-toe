package by.xxx.pupil.ai

import by.xxx.pupil.Constants
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

interface AIPlayer {

    fun nextMove(board: Board, player: Player, maxDepth: Int): Move

    fun nextMove(board: Board, player: Player): Move {
        return nextMove(board, player, Constants.DEFAULT_MINIMAX_DEPTH_LIMIT)
    }

    fun nextMove(board: Board): Move {
        return nextMove(board, Constants.DEFAULT_AI_TYPE, Constants.DEFAULT_MINIMAX_DEPTH_LIMIT)
    }

}