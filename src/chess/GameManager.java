package chess;

import org.apache.log4j.Logger;

import chess.game.ChessGame;
import chess.game.GameObserver;
import chess.player.Player;
import chess.util.Teams;

public class GameManager {
    static Logger logger = Logger.getLogger(GameManager.class);
    static GameObserver observer;
    public static void main(String[] args) throws InterruptedException {
        ChessGame game = createChessGame();
        game.start();
        if (observer.hasFinished()){
            System.out.println("Game finished");
        }
    }

    private static ChessGame createChessGame(){
        Player playerSilver = new Player("Player 1", Teams.SILVER);
        Player playerGold = new Player("Player 2", Teams.GOLD);

        ChessGame game = new ChessGame();
        game.setPlayers(playerSilver, playerGold);

        setObserver(game);
        return game;
    }

    private static void setObserver(ChessGame game) {
        observer = new GameObserver(game);
        game.attach(observer);
    }
}
