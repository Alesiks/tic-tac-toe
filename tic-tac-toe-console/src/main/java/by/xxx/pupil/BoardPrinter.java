package by.xxx.pupil;

import by.xxx.pupil.model.Board;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardPrinter {

    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Board board) {
        try {
            List<String> boardAsStrings = prepareForPrinting(board);
            for (String boardLine : boardAsStrings) {
                bufferedWriter.write(boardLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException("something wrong!");
        }
    }

    public List<String> prepareForPrinting(Board board) {
        List<String> boardAsString = new ArrayList<>();
        int width = board.getWidth();
        int height = board.getHeight();
        String firstLine = IntStream.iterate(1, i -> i + 1)
                .limit(width)
                .mapToObj(i -> i >= 10 ? String.valueOf(i) : i + " ")
                .collect(Collectors.joining("|", "   ", ""));
        boardAsString.add(firstLine);

        for (int i = 0; i < height; i++) {
            final int hIndex = i;
            String prefix = hIndex >= 9 ? String.valueOf(hIndex + 1) : (hIndex + 1) + " ";
            String line = IntStream.iterate(0, j -> j + 1)
                    .limit(width)
                    .mapToObj(j -> board.getCellValue(hIndex, j))
                    .map(cell -> cell.getSymbol() + " ")
                    .collect(Collectors.joining("|", prefix + " ", ""));
            boardAsString.add(line);
        }

        return boardAsString;
    }


}
