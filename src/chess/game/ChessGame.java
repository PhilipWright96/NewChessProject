package chess.game;

import java.util.Scanner;

import chess.board.ChessBoard;
import chess.player.Player;
import chess.util.Teams;

public class ChessGame {
    private ChessBoard board;
    private GameObserver observer;

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

    public void attach(GameObserver observer) {
        this.observer = observer;
    }

    public void start() throws InterruptedException {
        this.board = new ChessBoard();

        turnsTaken = 0;
        this.isRunning = true;

        playRound();

        Thread.sleep(5000);
        finish();
    }

    private void playRound() {
        Scanner userInputScanner = new Scanner(System.in);

        String silverResult = getValidInputFromPlayer(userInputScanner, playerSilver);
        System.out.println("Player Silver has played " + silverResult);

        String goldResult = getValidInputFromPlayer(userInputScanner, playerGold);
        System.out.println("Player Gold has played " + goldResult);
        
        this.turnsTaken++;
        userInputScanner.close();
    }

    private String getValidInputFromPlayer(Scanner userInputScanner, Player player){
        boolean inputValid = false;
        String input = null; 
        while (inputValid == false){
            input = player.getPlayerInput(userInputScanner);
            inputValid = InputChecker.checkPlayerInput(input, player, board);
        }
        return input;
    }

    private void finish() {
        this.isRunning = false;
        this.isFinished = true;

        observer.update();
    }
}