package by.pupil.converter

import by.pupil.Constants
import by.pupil.model.resolveCellTypeFromSymbol
import by.pupil.model.GameRequest
import by.pupil.model.Board
import org.apache.commons.lang3.Validate
import java.util.HashMap

class RequestConverter {
    fun toBoard(request: GameRequest): Board {
        val boardCells = request.board
        val board = Board(boardCells.size, boardCells[0].size)
        for (i in boardCells.indices) {
            for (j in boardCells[i].indices) {
                board.updateCellValue(i, j, resolveCellTypeFromSymbol(boardCells[i][j]))
            }
        }
        return board
    }

    fun toGameProperties(request: GameRequest): Map<String, Any> {
        Validate.isTrue(
            request.difficultyLevel > 0 && request.difficultyLevel < 10,
            "difficulty level is out of range"
        )
        val depth = if (request.difficultyLevel != 0) request.difficultyLevel else Constants.DEFAULT_MINIMAX_DEPTH_LIMIT
        val props: MutableMap<String, Any> = HashMap()
        props[Constants.MINIMAX_DEPTH_PROPERTY] = depth
        return props
    }
}