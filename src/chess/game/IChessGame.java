package chess.game;

public interface IChessGame {
    public void attach(GameObserver observer);
    public void start() throws InterruptedException;
}
