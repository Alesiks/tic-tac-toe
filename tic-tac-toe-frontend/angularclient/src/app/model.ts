export class GameStateRequest {
    board: string[][];
    playerMove: Move;
    difficultyLevel: number;

    constructor(board: string[][], playerMove: Move, difficultyLevel: number) {
        this.board = board;
        this.playerMove = playerMove;
        this.difficultyLevel = difficultyLevel;
    }
}

export class GameStateResponse {
    board: string[][]
    aiMove: Move;
    gameStatus: string;
    winningSequence: Move[];
}

export class Move {
    y: number;
    x: number;
}