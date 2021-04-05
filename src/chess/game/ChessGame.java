package chess.game;

import chess.board.IChessBoard;
import chess.input.ClearPathChecker;
import chess.input.InputRetriever;
import chess.player.Player;
import chess.util.Teams;

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
        }
        System.out.println(
            "Player Silver has played " +
            silverMove.getMoveFromColumn() +
            " " +
            silverMove.getMoveFromRow() +
            " to " +
            silverMove.getMoveToColumn() +
            " " +
            silverMove.getMoveToRow()
        );

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
        }
        System.out.println(
            "Player Gold has played " +
            goldMove.getMoveFromColumn() +
            " " +
            goldMove.getMoveFromRow() +
            " to " +
            goldMove.getMoveToColumn() +
            " " +
            goldMove.getMoveToRow()
        );
        turnsTaken++;
    }
}
