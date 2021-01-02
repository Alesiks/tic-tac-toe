package by.xxx.pupil;

import by.xxx.pupil.ai.AIPlayer;
import by.xxx.pupil.ai.minimax.MinimaxBasedAI;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.Move;

import java.io.*;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws IOException {
        Board board = new Board(
                10,
                10,
                Constants.DEFAULT_WIN_SEQUENCE_LENGTH
        );

        AIPlayer AIPlayer = new MinimaxBasedAI();
        WinnerFinder winnerFinder = new WinnerFinder();

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

            boolean crossWin = winnerFinder.isMoveLeadToWin(board, new Move(h - 1, w - 1));
            if (crossWin) {
                System.out.println("Crosses win!");
                break;
            }

            Move aiMove = AIPlayer.nextMove(board);
            board.updateCellValue(aiMove.getI(), aiMove.getJ(), CellType.NOUGHT);
            printer.print(board);

            boolean noughtWin = winnerFinder.isMoveLeadToWin(board, aiMove);
            if (noughtWin) {
                System.out.println("Noughts win!");
                break;
            }
        }


    }


}
