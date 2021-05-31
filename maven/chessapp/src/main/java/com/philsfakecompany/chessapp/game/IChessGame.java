package game;

public interface IChessGame {
    public void attach(GameObserver observer);

    public void playGame() throws InterruptedException;
}
