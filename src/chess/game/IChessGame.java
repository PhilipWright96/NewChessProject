package chess.game;

import chess.player.Player;

public interface IChessGame {
    public void setPlayers(Player playerOne, Player playerTwo);
    public void attach(GameObserver observer);
    public void start();
}
