import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { map, catchError} from 'rxjs/operators';
import { GameStateRequest, GameStateResponse, Move } from './model';
import { constants } from './constants';

@Injectable()
export class GameService{
    private gameUrl = '/api/play';

    board: string[][];
    lastMove: CellType[][];
    winningSequence: boolean[][];
    result: string;
    aiThink: boolean;
    difficultyLevelDisabled: boolean = false;
    private difficultyLevel: number = constants.DEFAULT_DIFFICULTY_LEVEL;
    private currentGameState: CurrentGameState = new CurrentGameState()
    private gameStarted: boolean

    constructor(private http: HttpClient) {
        this.aiThink = false
        this.board = []
        this.lastMove = []
        this.winningSequence = []
        this.gameStarted = false;
        for(var i: number = 0; i < 10; i++) {
            this.board[i] = [];
            this.lastMove[i] = [];
            this.winningSequence[i] = [];
            for(var j: number = 0; j < 10; j++) {
                this.board[i][j] = ' ';
                this.lastMove[i][j] = CellType.Empty;
                this.winningSequence[i][j] = false;
            }
        }
    }

    makeMove(y: number, x: number) {
        console.log("Player move (y: " + y + ", x: " + x + ")");

        if(this.isMovePossible(y, x) && !this.aiThink) {
            this.gameStarted = true;
            this.difficultyLevelDisabled = true;
            this.aiThink = true;
            this.makePlayerMove(y, x);

            const requestBody = new GameStateRequest(this.board, {y: y, x: x}, this.difficultyLevel);
            const httpOptions = {}

            if(this.currentGameState.lastAiMove != null) {
                this.lastMove[this.currentGameState.lastAiMove.y][this.currentGameState.lastAiMove.x] = CellType.Empty
            }
            this.http.post(this.gameUrl, requestBody, httpOptions).subscribe(
                (data: GameStateResponse) => {
                    this.handleAiResponse(data)
                },
                error => console.log(error)
            );
        }
    }

    enableObstacles(isObstaclesEnabled: boolean) {
        if(isObstaclesEnabled && !this.gameStarted) {
            let obstaclesCount = Math.floor(Math.random() * (constants.MAX_OBSTACLES_COUNT + 1));
            for (let i = 0; i < obstaclesCount; i++) {
                let y = Math.floor(Math.random() * (constants.DEFAULT_BOARD_HEIGHT));
                let x = Math.floor(Math.random() * (constants.DEFAULT_BOARD_WIDTH));
                this.board[y][x] = '#';
            }
        } else if(!isObstaclesEnabled && !this.gameStarted) {
            for(var i: number = 0; i < constants.DEFAULT_BOARD_HEIGHT; i++) {
                for(var j: number = 0; j < constants.DEFAULT_BOARD_WIDTH; j++) {
                    this.board[i][j] = ' ';
                }
            }
        }
    }

    setDifficultyLevel(level: number) {
        if(!this.gameStarted) {
            if(level == 1) {
                this.difficultyLevel = 1
            } else if(level == 2) {
                this.difficultyLevel = 3;
            } else if(level == 3) {
                this.difficultyLevel = 5;
            }  else if(level == 4) {
                this.difficultyLevel = 7;
            }
            console.log("difficulty level:" + this.difficultyLevel)
        }
    }

    private isMovePossible(y: number, x: number): boolean {
        if(y < 0 || y >= constants.DEFAULT_BOARD_HEIGHT
            || x < 0 || x >= constants.DEFAULT_BOARD_WIDTH
            || this.result == 'You won!'
            || this.result == 'You lost!'
            || this.result == 'It is a draw!') {
            return false;
        } else if(this.board[y][x] != ' ') {
            return false;
        }
        return true;
    }

    private makePlayerMove(y: number, x: number) {
        if(this.currentGameState.lastPlayerMove != null) {
            this.lastMove[this.currentGameState.lastPlayerMove.y][this.currentGameState.lastPlayerMove.x] = CellType.Empty
        }

        this.currentGameState.lastPlayerMove = {y: y, x: x}
        this.lastMove[y][x] = CellType.Player
        this.board[y][x] = 'x';
    }

    private handleAiResponse(data: GameStateResponse) {
        if(data.gameStatus == 'CROSS_WIN') {
            this.result = 'You won!'
        } else if(data.gameStatus == 'NOUGHT_WIN') {
            this.result = 'You lost!'
        } else if(data.gameStatus == 'DRAW') {
            this.result = 'It is a draw!'
        }
        if(data.aiMove != null) {
            this.lastMove[data.aiMove.y][data.aiMove.x] = CellType.Ai
            this.board[data.aiMove.y][data.aiMove.x] = '0';
            this.currentGameState.lastAiMove = {y: data.aiMove.y, x: data.aiMove.x}
        }
        if(data.winningSequence != null) {
            for (var move of data.winningSequence) {
               this.winningSequence[move.y][move.x] = true;
            }
        }
        this.aiThink = false;
        console.log(data);
    }

}


export class CurrentGameState {
    lastAiMove: Move
    lastPlayerMove: Move

}

enum CellType {
    Player='player',
    Ai='ai',
    Empty='empty'
}
