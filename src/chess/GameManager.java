package chess;

import org.apache.log4j.Logger;

import chess.game.ChessGame;
import chess.player.Player;
import chess.util.Teams;

public class GameManager {
    static Logger logger = Logger.getLogger(GameManager.class);
    public static void main(String[] args){
        ChessGame game = createChessGame();
        game.start();
    }

    private static ChessGame createChessGame(){
        Player playerSilver = new Player("Player 1", Teams.SILVER);
        Player playerGold = new Player("Player 1", Teams.GOLD);

        return new ChessGame(playerSilver, playerGold);
    }
}
