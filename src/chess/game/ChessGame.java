package chess.game;

import chess.board.ChessBoard;

public class ChessGame {
    private ChessBoard board;
    
    private boolean isRunning = false;
    private boolean isFinished = false; 

    public void start() {
        this.board = new ChessBoard();
        board.addPiecesToBoard();

        this.isRunning = true;
    }

    public void finish() {
        this.isRunning = false;
        this.isFinished = true;
    }
}