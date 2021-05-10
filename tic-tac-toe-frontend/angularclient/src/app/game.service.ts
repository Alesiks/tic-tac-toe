import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { map, catchError} from 'rxjs/operators';
import { GameStateRequest, GameStateResponse, Move } from './model';
import { constants } from './constants';

@Injectable()
export class GameService{
    private gameUrl = 'http://localhost:8080/api/play';

    board: string[][];
    lastMove: CellType[][];
    result: string
    aiThink: boolean
    private currentGameState: CurrentGameState = new CurrentGameState()

    constructor(private http: HttpClient) {
        this.aiThink = false
        this.board = []
        this.lastMove = []
        for(var i: number = 0; i < 10; i++) {
            this.board[i] = [];
            this.lastMove[i] = [];
            for(var j: number = 0; j< 10; j++) {
                this.board[i][j] = ' ';
                this.lastMove[i][j] = CellType.Empty;
            }
        }
    }

    makeMove(y: number, x: number) {
        console.log("Player move (y: " + y + ", x: " + x + ")");

        if(this.isMovePossible(y, x) && !this.aiThink) {
            this.aiThink = true;
            this.makePlayerMove(y, x);

            const requestBody = new GameStateRequest(this.board, {y: y, x: x}, constants.DEFAULT_DIFFICULTY_LEVEL);
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

    private isMovePossible(y: number, x: number): boolean {
        if(y < 0 || y >= constants.DEFAULT_BOARD_HEIGHT
            || x < 0 || x >= constants.DEFAULT_BOARD_WIDTH
            || this.result == 'Crosses win!' 
            || this.result == 'Noughts win!'
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
            this.result = 'Crosses win!'
        } else if(data.gameStatus == 'NOUGHT_WIN') {
            this.result = 'Noughts win!'
        } else if(data.gameStatus == 'DRAW') {
            this.result = 'It is a draw!'
        }
        this.lastMove[data.aiMove.y][data.aiMove.x] = CellType.Ai
        this.board[data.aiMove.y][data.aiMove.x] = '0';
        this.currentGameState.lastAiMove = {y: data.aiMove.y, x: data.aiMove.x}
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