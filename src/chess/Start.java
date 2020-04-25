package chess;

import org.apache.log4j.Logger;

public class Start {
    static Logger logger = Logger.getLogger(Start.class);
    public static void main(String[] args){
        Board board = new Board();
        board.showBoard();
    }
}
