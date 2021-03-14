package by.xxx.pupil.ai

import by.xxx.pupil.Constants
import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

interface AIPlayer {

    fun nextMove(board: Board, player: Player): Move

    fun nextMove(board: Board): Move {
        return nextMove(board, Constants.DEFAULT_AI_TYPE)
    }

}