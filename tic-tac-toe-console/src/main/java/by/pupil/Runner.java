package by.pupil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import by.pupil.ai.AIPlayer;
import by.pupil.model.Board;
import by.pupil.model.CellType;
import by.pupil.model.Move;
import by.pupil.model.Player;
import by.pupil.winning.WinnerFinder;

public class Runner {

    private WinnerFinder winnerFinder;

    private AIPlayer aiPlayer;

    public static void main(String[] args) {
    }

    public void run() throws Exception {
        Board board = new Board(10, 10);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BoardPrinter printer = new BoardPrinter();

        System.out.println("The tic-tac-toe game starts, enjoy!");
        System.out.println("Chose difficulty level, number from 1 to 10: ");

        int difficulty = Integer.parseInt(bufferedReader.readLine().replaceAll("\\s+$", ""));
        printer.print(board);


        while (true) {
            System.out.println("Make your step, enter two numbers (y x)");
            String[] line = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            int[] parsedToInt = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
            int h = parsedToInt[0];
            int w = parsedToInt[1];
            board.updateCellValue(h - 1, w - 1, CellType.CROSS);
            printer.print(board);

            boolean crossWin = winnerFinder.isMoveLeadToWin(board, new Move(h - 1, w - 1, Player.CROSSES));
            if (crossWin) {
                System.out.println("Crosses win!");
                break;
            }

            Map<String, Object> props = new HashMap<>();
            props.put(Constants.MINIMAX_DEPTH_PROPERTY, difficulty);
            Move aiMove = aiPlayer.nextMove(board, Player.NOUGHTS, props);
            board.updateCellValue(aiMove.getY(), aiMove.getX(), CellType.NOUGHT);
            printer.print(board);

            boolean noughtWin = winnerFinder.isMoveLeadToWin(board, aiMove);
            if (noughtWin) {
                System.out.println("Noughts win!");
                break;
            }
        }
    }
}
