package by.pupil

import by.pupil.ai.AIPlayer
import by.pupil.converter.RequestConverter
import by.pupil.model.*
import by.pupil.winning.WinnerFinder
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import java.util.stream.Collectors

fun main() {
    embeddedServer(Netty, port = 8000) {
        install(ContentNegotiation) {
            jackson()
        }
        install(Koin) {
            modules(gameModule)
        }

        val requestConverter by inject<RequestConverter>()
        val winnerFinder by inject<WinnerFinder>()
        val aiPlayer by inject<AIPlayer>()

        routing {
            get("/api/hello") {
                call.respondText("Hello, world!")
            }
            post("api/play") {
                val request = call.receive<GameRequest>()
                val board: Board = requestConverter.toBoard(request)
                val playerMove = Move(request.playerMove.y, request.playerMove.x, Player.CROSSES)

                val res: GameResponse =
                    if (winnerFinder.isMoveLeadToWin(board, playerMove)) {
                        val winningSequence = winnerFinder.getWinSequenceForMove(board, playerMove)
                            .stream()
                            .map { (y, x): Cell -> WebCell(y, x) }
                            .collect(Collectors.toList())
                        GameResponse(GameStatus.CROSS_WIN, request.board, null, winningSequence)
                    } else {
                        val aiMove =
                            aiPlayer.nextMove(board, Player.NOUGHTS, requestConverter.toGameProperties(request))
                        val boardCells: Array<Array<Char>> = request.board
                        boardCells[aiMove.y][aiMove.x] = CellType.NOUGHT.symbol
                        if (winnerFinder.isMoveLeadToWin(board, aiMove)) {
                            val winningSequence = winnerFinder.getWinSequenceForMove(board, aiMove).stream()
                                .map { (y, x): Cell -> WebCell(y, x) }
                                .collect(Collectors.toList())
                            GameResponse(
                                GameStatus.NOUGHT_WIN,
                                boardCells,
                                WebCell(aiMove.y, aiMove.x),
                                winningSequence
                            )
                        } else {
                            GameResponse(
                                GameStatus.GAME_CONTINUES,
                                boardCells,
                                WebCell(aiMove.y, aiMove.x),
                                null
                            )
                        }
                    }
                call.respond(res)
            }
        }
    }.start(wait = true)
}