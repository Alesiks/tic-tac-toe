package by.pupil

import by.pupil.ai.AIPlayer
import by.pupil.converter.RequestConverter
import by.pupil.model.*
import by.pupil.service.PersonToAIGameService
import by.pupil.winning.WinnerFinder
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.plugin.Koin
import java.time.Duration
import java.util.stream.Collectors

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson()
        }
        install(Koin) {
            modules(
                webModule,
                minimaxModule,
                coreModule,
                repositoryModule
            )
        }
        install(CORS) {
        }
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(15)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }

        val requestConverter: RequestConverter by inject(clazz = RequestConverter::class.java)
        val winnerFinder: WinnerFinder by inject(clazz = WinnerFinder::class.java)
        val aiPlayer: AIPlayer by inject(clazz = AIPlayer::class.java)
        val personToAIGameService: PersonToAIGameService by inject(PersonToAIGameService::class.java)

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
        }
    }.start(wait = true)
}
