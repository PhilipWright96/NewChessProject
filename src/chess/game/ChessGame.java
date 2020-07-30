package chess.game;

import chess.board.ChessBoard;
import chess.player.Player;
import chess.util.Teams;

public class ChessGame {
    private ChessBoard board;

    private Player playerSilver;
    private Player playerGold;
    
    private boolean isRunning = false;
    private boolean isFinished = false; 
    private int turnsTaken;

    public ChessGame(Player playerOne, Player playerTwo){
        if (playerOne.getTeam() == Teams.SILVER){
            this.playerSilver = playerOne;
            this.playerGold = playerTwo;
        }
        else {
            this.playerSilver = playerTwo;
            this.playerGold = playerOne;
        }
    }

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