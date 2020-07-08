package chess;

import org.apache.log4j.Logger;
import chess.board.*;

public class GameManager {
    static Logger logger = Logger.getLogger(GameManager.class);
    public static void main(String[] args){
        ChessBoard board = new ChessBoard();
        board.addPiecesToBoard();
    }
}
