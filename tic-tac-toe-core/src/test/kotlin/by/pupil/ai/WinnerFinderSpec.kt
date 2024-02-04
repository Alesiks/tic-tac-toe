package by.pupil.ai

import by.pupil.model.Board
import by.pupil.model.CellType.EMPTY
import by.pupil.model.CellType.NOUGHT
import by.pupil.model.Move
import by.pupil.model.Player
import by.pupil.winning.WinnerFinder
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WinnerFinderSpec : FunSpec({

    val winnerFinder = WinnerFinder()

    test("Board is empty, no winners") {
        val board =
            Board(
                arrayOf(
                    arrayOf(NOUGHT, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                    arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
                ),
            )

        winnerFinder.isMoveLeadToWin(board, Move(0, 0, Player.NOUGHTS)) shouldBe false
    }
})
