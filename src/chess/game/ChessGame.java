package chess.game;

import chess.board.IChessBoard;
import chess.player.Player;
import chess.util.Teams;
import chess.input.InputRetriever;

public class ChessGame implements IChessGame{
    private IChessBoard board;
    private GameObserver observer;

    private Player playerSilver;
    private Player playerGold;

    private InputRetriever inputRetriever;
    
    private boolean isRunning = false;
    private boolean isFinished = false; 
    private int turnsTaken;

    public ChessGame(Player playerOne, Player playerTwo, IChessBoard board, InputRetriever inputRetriever){
        if (playerOne.getTeam() == Teams.SILVER){
            playerSilver = playerOne;
            playerGold = playerTwo;
        }
        else {
            playerSilver = playerTwo;
            playerGold = playerOne;
        }
        this.board = board;
        this.inputRetriever = inputRetriever;
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

        inputRetriever.closeScanner();

        isRunning = false;
        isFinished = true;

        observer.update();
    }

    private void playRound() {
        ChessMove silverMove = inputRetriever.getValidInputFromPlayer(playerSilver, board);
        board.movePiece(silverMove);
        if (observer.opposingKingInCheck(playerSilver, board)){
            System.out.println("Silver put gold in check");
        };

        System.out.println("Player Silver has played " + silverMove.getMoveFromColumn() + " " + silverMove.getMoveFromRow() + " to " + silverMove.getMoveToColumn() + " " + silverMove.getMoveToRow());

        ChessMove goldMove = inputRetriever.getValidInputFromPlayer(playerGold, board);
        board.movePiece(goldMove);
        if (observer.opposingKingInCheck(playerGold, board)){
            System.out.println("Gold put silver in check");
        };

        System.out.println("Player Gold has played " + goldMove.getMoveFromColumn() + " " + goldMove.getMoveFromRow() + " to " + goldMove.getMoveToColumn() + " " + goldMove.getMoveToRow());
        
        turnsTaken++;
    }
}