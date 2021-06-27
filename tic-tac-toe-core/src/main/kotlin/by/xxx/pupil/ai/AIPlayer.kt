package by.xxx.pupil.ai

import by.xxx.pupil.model.Board
import by.xxx.pupil.model.Move
import by.xxx.pupil.model.Player

interface AIPlayer {

    /**
     * @param board - describes current game state
     * @param player - whose turn to make a move
     * @param properties - additional properties needed for your algorithm
     */
    fun nextMove(board: Board, player: Player, properties: Map<String, Any>): Move

}