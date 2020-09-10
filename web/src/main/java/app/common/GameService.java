package app.common;

public interface GameService {
    boolean isGameOver();
    boolean isGameWon();
    String getMainMessage();
    String getResultMessage();
    void checkGuess(int guess);
    void reset();
}
