package chess;

import org.apache.log4j.Logger;

public class GameManager {
    static Logger logger = Logger.getLogger(GameManager.class);
    public static void main(String[] args){
        Board board = new Board();
        board.showBoard();
    }
}
