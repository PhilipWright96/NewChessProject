package chess;

import org.apache.log4j.Logger;

import chess.game.ChessGame;

public class GameManager {
    static Logger logger = Logger.getLogger(GameManager.class);
    public static void main(String[] args){
        ChessGame game = new ChessGame();
        game.start();
    }
}
