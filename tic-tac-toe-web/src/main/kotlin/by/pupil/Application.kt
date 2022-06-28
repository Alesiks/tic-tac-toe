package by.pupil

import by.pupil.ai.AIPlayer
import by.pupil.converter.RequestConverter
import by.pupil.model.*
import by.pupil.plugins.Koin
import by.pupil.plugins.inject
import by.pupil.service.PersonToAIGameService
import by.pupil.winning.WinnerFinder
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import java.util.stream.Collectors

fun main() {
    embeddedServer(Netty, port = 8000) {
        install(ContentNegotiation) {
            jackson()
        }
        install(Koin) {
            modules = arrayListOf(
                webModule, minimaxModule, coreModule, repositoryModule
            )
        }
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(15)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }

        val requestConverter: RequestConverter by inject()
        val winnerFinder: WinnerFinder by inject()
        val aiPlayer: AIPlayer by inject()
        val personToAIGameService: PersonToAIGameService by inject()

        routing {
            get("/api/health") {
                call.respond(WebCell(1, 1))
            }
            post("/api/play") {
                val request = call.receive<GameRequest>()
                val board: Board = requestConverter.toBoard(request)
                val playerMove = Move(request.playerMove.y, request.playerMove.x, Player.CROSSES)

//                personToAIGameService.startUserWithAIGame(
//                    1,
//                    request.difficultyLevel
//                )

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
            // to show that webscokets works
            webSocket("/echo") {
                send("Please enter your name")
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val receivedText = frame.readText()
                            if (receivedText.equals("bye", ignoreCase = true)) {
                                close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                            } else {
                                send(Frame.Text("Hi, $receivedText!"))
                            }
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}
