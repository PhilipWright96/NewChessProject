package chess;

import org.apache.log4j.Logger;

import chess.game.ChessGame;
import chess.game.GameObserver;
import chess.game.IChessGame;
import chess.player.Player;
import chess.util.Teams;

public class GameManager {
    static Logger logger = Logger.getLogger(GameManager.class);
    static GameObserver observer;
    public static void main(String[] args) throws InterruptedException {
        IChessGame game = createChessGame();
        game.start();
        if (observer.hasFinished()){
            System.out.println("Game finished");
        }
    }

    private static IChessGame createChessGame(){
        Player playerSilver = new Player("Player 1", Teams.SILVER);
        Player playerGold = new Player("Player 2", Teams.GOLD);

        IChessGame game = new ChessGame();
        game.setPlayers(playerSilver, playerGold);

        setObserver(game);
        return game;
    }

    private static void setObserver(IChessGame game) {
        observer = new GameObserver(game);
        game.attach(observer);
    }
}
