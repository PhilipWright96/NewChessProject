package chess.game;

public class GameObserver {

    IChessGame game;
    boolean hasFinished = false;

    public GameObserver(IChessGame game) {
        this.game = game;
        this.game.attach(this);
    }

    public void update() {
        hasFinished = true;
    }

    public boolean hasFinished() {
        return hasFinished;
    }
}
