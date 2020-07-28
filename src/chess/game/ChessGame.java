package chess.game;

import chess.board.ChessBoard;

public class ChessGame {
    private ChessBoard board;
    
    private boolean isRunning = false;
    private boolean isFinished = false; 
    private int turnsTaken; 

    public void start() {
        this.board = new ChessBoard();

        turnsTaken = 0;
        this.isRunning = true;
    }

    public void finish() {
        this.isRunning = false;
        this.isFinished = true;
    }
}