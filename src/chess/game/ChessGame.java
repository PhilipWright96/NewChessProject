package chess.game;

import java.util.Scanner;

import chess.board.ChessBoard;
import chess.board.IChessBoard;
import chess.board.ChessBoardGUI;
import chess.player.Player;
import chess.util.Teams;

public class ChessGame implements IChessGame{
    private IChessBoard board;
    private GameObserver observer;

    private Player playerSilver;
    private Player playerGold;

    private Scanner userInputScanner = new Scanner(System.in);
    
    private boolean isRunning = false;
    private boolean isFinished = false; 
    private int turnsTaken;

    public ChessGame(Player playerOne, Player playerTwo){
        if (playerOne.getTeam() == Teams.SILVER){
            playerSilver = playerOne;
            playerGold = playerTwo;
        }
        else {
            playerSilver = playerTwo;
            playerGold = playerOne;
        }
    }

    public void attach(GameObserver observer) {
        this.observer = observer;
    }

    public void playGame() throws InterruptedException {
        board = new ChessBoard(new ChessBoardGUI());
        board.initializeChessBoard();

        turnsTaken = 0;
        isRunning = true;

        for (int i = 0; i < 5; i++){
            playRound();
        }
        userInputScanner.close();

        Thread.sleep(5000);
        finish();
    }

    private void playRound() {
        ChessMove silverMove = getValidInputFromPlayer(userInputScanner, playerSilver);
        movePiece(silverMove);
        System.out.println("Player Silver has played " + silverMove.getMoveFromColumn() + " " + silverMove.getMoveFromRow() + " to " + silverMove.getMoveToColumn() + " " + silverMove.getMoveToRow());

        ChessMove goldMove = getValidInputFromPlayer(userInputScanner, playerGold);
        movePiece(goldMove);
        System.out.println("Player Gold has played " + goldMove.getMoveFromColumn() + " " + goldMove.getMoveFromRow() + " to " + goldMove.getMoveToColumn() + " " + goldMove.getMoveToRow());
        
        turnsTaken++;
    }

    private ChessMove getValidInputFromPlayer(Scanner userInputScanner, Player player){
        boolean inputValid = false;
        String input = null; 
        while (inputValid == false){
            input = player.getPlayerInput(userInputScanner);
            inputValid = InputChecker.checkPlayerInput(input, player, board);
        }
        return new ChessMove(input);
    }

    private void movePiece(ChessMove inputMove){
        board.movePiece(inputMove);
    }

    private void finish() {
        isRunning = false;
        isFinished = true;

        observer.update();
    }
}