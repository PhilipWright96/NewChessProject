package game;

import board.*;
import input.*;
import player.*;
import util.*;

public class ChessGame implements IChessGame {

    private IChessBoard board;
    private GameObserver observer;

    private Player playerSilver;
    private Player playerGold;

    private InputRetriever inputRetriever;
    private ICheckChecker checkChecker;

    private boolean isRunning = false;
    private boolean isFinished = false;
    private int turnsTaken;

    public ChessGame(
        Player playerOne,
        Player playerTwo,
        IChessBoard board,
        InputRetriever inputRetriever,
        ICheckChecker checkChecker
    ) {
        if (playerOne.getTeam() == Teams.SILVER) {
            playerSilver = playerOne;
            playerGold = playerTwo;
        } else {
            playerSilver = playerTwo;
            playerGold = playerOne;
        }
        this.board = board;
        this.inputRetriever = inputRetriever;
        this.checkChecker = checkChecker;
    }

    public void attach(GameObserver observer) {
        this.observer = observer;
    }

    public void playGame() throws InterruptedException {
        board.initializeChessBoard();

        turnsTaken = 0;
        isRunning = true;

        for (int i = 0; i < 5; i++) {
            playRound();
        }

        inputRetriever.closeScanner();

        isRunning = false;
        isFinished = true;

        observer.update();
    }

    private void playRound() {
        ChessMove silverMove = inputRetriever.getValidInputFromPlayer(
            playerSilver,
            board,
            checkChecker
        );
        board.movePiece(silverMove, true);

        if (
            checkChecker.opposingKingInCheck(
                playerSilver,
                board,
                new ClearPathChecker()
            )
        ) {
            System.out.println("Silver put gold in check");
            if (
                checkChecker.opposingKingInCheckmate(
                    playerSilver,
                    board,
                    new ClearPathChecker()
                )
            ) {
                System.out.println("Silver put gold in checkmate!");
            }
        }

        ChessMove goldMove = inputRetriever.getValidInputFromPlayer(
            playerGold,
            board,
            checkChecker
        );

        board.movePiece(goldMove, true);
        if (
            checkChecker.opposingKingInCheck(
                playerGold,
                board,
                new ClearPathChecker()
            )
        ) {
            System.out.println("Gold put silver in check");
            if (
                checkChecker.opposingKingInCheckmate(
                    playerGold,
                    board,
                    new ClearPathChecker()
                )
            ) {
                System.out.println("Gold put silver in checkmate!");
            }
        }
        turnsTaken++;
    }
}
