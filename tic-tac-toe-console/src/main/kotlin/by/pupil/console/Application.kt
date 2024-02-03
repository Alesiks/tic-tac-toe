package by.pupil.console

import by.pupil.Constants
import by.pupil.ai.AIPlayer
import by.pupil.coreModule
import by.pupil.minimaxModule
import by.pupil.model.Board
import by.pupil.model.CellType
import by.pupil.model.Move
import by.pupil.model.Player
import by.pupil.winning.WinnerFinder
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    startKoin {
        modules(minimaxModule, coreModule)
    }

    val winnerFinder : WinnerFinder by inject(clazz = WinnerFinder::class.java)
    val aiPlayer: AIPlayer by inject(clazz = AIPlayer::class.java)

    val board = Board(10, 10)
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val printer = BoardPrinter()
    println("The tic-tac-toe game starts, enjoy!")
    println("Chose difficulty level, number from 1 to 10: ")
    val difficulty = bufferedReader.readLine().replace("\\s+$".toRegex(), "").toInt()
    printer.print(board)
    while (true) {
        println("Make your step, enter two numbers (y x)")
        val line = bufferedReader.readLine().replace("\\s+$".toRegex(), "").split(" ".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val parsedToInt = Arrays.stream(line).mapToInt { s: String -> s.toInt() }.toArray()
        val h = parsedToInt[0]
        val w = parsedToInt[1]
        board.updateCellValue(h - 1, w - 1, CellType.CROSS)
        printer.print(board)
        val crossWin = winnerFinder!!.isMoveLeadToWin(board, Move(h - 1, w - 1, Player.CROSSES))
        if (crossWin) {
            println("Crosses win!")
            break
        }
        val props: MutableMap<String, Any> = HashMap()
        props[Constants.MINIMAX_DEPTH_PROPERTY] = difficulty
        val aiMove = aiPlayer!!.nextMove(board, Player.NOUGHTS, props)
        board.updateCellValue(aiMove.y, aiMove.x, CellType.NOUGHT)
        printer.print(board)
        val noughtWin = winnerFinder.isMoveLeadToWin(board, aiMove)
        if (noughtWin) {
            println("Noughts win!")
            break
        }
    }

}
