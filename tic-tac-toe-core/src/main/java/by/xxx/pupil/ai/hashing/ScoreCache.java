package by.xxx.pupil.ai.hashing;

import by.xxx.pupil.model.Board;

public interface ScoreCache {

    Integer getScore(Board board);

    Integer getScore(Board board, long boardHash);

    void putScore(Board board, int score);

    void putScore(long boardHash, int score);

}
