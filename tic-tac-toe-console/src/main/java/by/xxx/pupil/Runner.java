package by.xxx.pupil;

import by.xxx.pupil.ai.Move;
import by.xxx.pupil.ai.NextMoveFinder;
import by.xxx.pupil.ai.minimax.MinimaxAlgorithmBasedAI;

import java.io.*;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws IOException {
        Board board = new Board(
                10,
                10,
                Constants.DEFAULT_WIN_SEQUENCE_LENGTH
        );

        NextMoveFinder nextMoveFinder = new MinimaxAlgorithmBasedAI();
        GameStrategy gameStrategy = new GameStrategy();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        BoardPrinter printer = new BoardPrinter();

        System.out.println("The tic-tac-toe game starts, enjoy!");
        System.out.println("The difficulty level was: 5");
        printer.print(board);


        while (true) {
            System.out.println("Make your step, enter two numbers (y x)");
            String[] line = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            int[] parsedToInt = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
            int h = parsedToInt[0];
            int w = parsedToInt[1];
            board.updateCellValue(h - 1, w - 1, CellType.CROSS);
            printer.print(board);

            GameResult currState = gameStrategy.gameResult(board);
            if (GameResult.CROSS_WIN == currState) {
                System.out.println("Crosses win!");
                break;
            } else if (GameResult.DRAW == currState) {
                System.out.println("It's a draw!");
                break;
            }

            Move aiMove = nextMoveFinder.findNextMove(board);
            board.updateCellValue(aiMove.getI(), aiMove.getJ(), CellType.NOUGHT);
            printer.print(board);

            currState = gameStrategy.gameResult(board);
            if (GameResult.NOUGHT_WIN == currState) {
                System.out.println("Noughts win!");
                break;
            } else if (GameResult.DRAW == currState) {
                System.out.println("It's a draw!");
                break;
            }
        }


    }


}
