# tic-tac-toe
This is a custom implementation of a 5 in a row (or gomoku) game. 

Play here: https://game-five-in-a-row.herokuapp.com/

## Modules

- tic-tac-toe-core
- tic-tac-toe-playground - module to test algorithms and to run them against each other 
- tic-tac-core-console
- tic-tac-toe-web
- tic-tac-toe-repository
- tic-tac-toe-frontend

## AI
The AI is build on minimax algorithm and alpha-beta pruning.

## How to build project and play?
There is one game mode: Person vs AI. <br>

## Thoughts about AI

### possible combinations on a board:
straight four: -xxxx- <br>
four: 0xxxx- || -xxxx0 <br>
three: -xxx- <br>
broken three: -x-xx- || -xx-x- <br>

### minimax