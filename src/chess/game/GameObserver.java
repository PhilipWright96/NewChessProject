package chess.game;

public class GameObserver {
    ChessGame game;
    boolean hasFinished = false;
    public GameObserver(ChessGame game){
        this.game = game;
        this.game.attach(this);
    }

    public void update(){
        this.hasFinished = true;
    }

    public boolean hasFinished() {
        return this.hasFinished;
    }
}