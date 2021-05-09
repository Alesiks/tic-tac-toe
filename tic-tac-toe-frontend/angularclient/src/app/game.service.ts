import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { map, catchError} from 'rxjs/operators';
import { GameState } from './state';

@Injectable()
export class GameService{

    board: string[][];
    result: string
    private currState: GameState;
    private helloUrl = 'http://localhost:8080/api/hi';
    private gameUrl = 'http://localhost:8080/api/play';

    constructor(private http: HttpClient) {
        this.board = []
        for(var i: number = 0; i < 10; i++) {
            this.board[i] = [];
            for(var j: number = 0; j< 10; j++) {
                this.board[i][j] = ' ';
            }
        }
        console.log(this.board);
    }

    makeMove(y: number, x: number) {
        console.log(y + " -- " + x);
        this.board[y][x] = 'x';
        const body = {board: this.board, lastPlayerMove: {y: y, x: x}};
        console.log(body);

        let headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*'); 
        const httpOptions = {
            headers: headers
        }

        this.http.post(this.gameUrl, body, httpOptions).subscribe(
            (data: GameState) => {
                this.currState = data;
                if(data.gameState == 'CROSS_WIN') {
                    this.result = 'Crosses win!'
                } else if(data.gameState == 'NOUGHT_WIN') {
                    this.result = 'Noughts win!'
                } else if(data.gameState == 'DRAW') {
                    this.result = 'It is a draw!'
                }
                this.board[data.aiMove.y][data.aiMove.x] = '0';
                console.log(this.currState); 
            },
            error => console.log(error)
        );
        console.log(this.currState);
    }

}
