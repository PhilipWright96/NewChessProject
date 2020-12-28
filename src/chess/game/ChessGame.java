package chess.game;

import chess.board.IChessBoard;
import chess.player.Player;
import chess.util.Teams;

public class ChessGame implements IChessGame{
    private IChessBoard board;
    private GameObserver observer;

    private Player playerSilver;
    private Player playerGold;

    private InputChecker inputChecker;
    
    private boolean isRunning = false;
    private boolean isFinished = false; 
    private int turnsTaken;

    public ChessGame(Player playerOne, Player playerTwo, IChessBoard board, InputChecker inputChecker){
        if (playerOne.getTeam() == Teams.SILVER){
            playerSilver = playerOne;
            playerGold = playerTwo;
        }
        else {
            playerSilver = playerTwo;
            playerGold = playerOne;
        }
        this.board = board;
        this.inputChecker = inputChecker;
    }

    public void attach(GameObserver observer) {
        this.observer = observer;
    }

    public void playGame() throws InterruptedException {
        board.initializeChessBoard();

        turnsTaken = 0;
        isRunning = true;

        for (int i = 0; i < 5; i++){
            playRound();
        }

        inputChecker.closeScanner();

        isRunning = false;
        isFinished = true;

        observer.update();
    }

    private void playRound() {
        ChessMove silverMove = inputChecker.getValidInputFromPlayer(playerSilver, board);
        board.movePiece(silverMove);
        System.out.println("Player Silver has played " + silverMove.getMoveFromColumn() + " " + silverMove.getMoveFromRow() + " to " + silverMove.getMoveToColumn() + " " + silverMove.getMoveToRow());
        ChessMove goldMove = inputChecker.getValidInputFromPlayer(playerGold, board);
        board.movePiece(goldMove);
        System.out.println("Player Gold has played " + goldMove.getMoveFromColumn() + " " + goldMove.getMoveFromRow() + " to " + goldMove.getMoveToColumn() + " " + goldMove.getMoveToRow());
        
        turnsTaken++;
    }
}