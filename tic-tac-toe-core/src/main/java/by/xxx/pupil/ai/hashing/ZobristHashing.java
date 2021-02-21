package by.xxx.pupil.ai.hashing;

import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.Move;

import java.util.Random;

public class ZobristHashing {

    private final long[][][] transpositionTable;

    private static final int NUMBER_OF_PLAYERS = 2;

    public ZobristHashing(int height, int width) {
        transpositionTable = new long[height][width][NUMBER_OF_PLAYERS];

        Random rd = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int p = 0; p < NUMBER_OF_PLAYERS; p++) {
                    transpositionTable[i][j][p] = Math.abs(rd.nextLong());
                }
            }
        }
    }

    public long hash(Board board) {
        long hash = 0;

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                switch (board.getCellValue(i,j)) {
                    case CROSS:
                        hash ^= transpositionTable[i][j][0];
                    case NOUGHT:
                        hash ^= transpositionTable[i][j][1];
                    default:
                        break;
                }
            }
        }

        return hash;
    }

    public long updateHash(long hash, Move move) {
        if(move.isPerson()) {
            hash ^= transpositionTable[move.getI()][move.getJ()][0];
        } else {
            hash ^= transpositionTable[move.getI()][move.getJ()][1];
        }
        return hash;
    }


}
