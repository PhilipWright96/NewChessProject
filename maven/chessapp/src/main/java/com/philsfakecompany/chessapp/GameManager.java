import board.*;
import game.*;
import input.*;
import player.*;
import util.*;

public class GameManager {

    static GameObserver observer;

    public static void main(String[] args) throws InterruptedException {
        IChessGame game = createChessGame();
        game.playGame();
        if (observer.hasFinished()) {
            System.out.println("Game finished");
        }
    }

    private static IChessGame createChessGame() {
        Player playerSilver = new Player("Player 1", Teams.SILVER);
        Player playerGold = new Player("Player 2", Teams.GOLD);
        ChessBoard board = new BoardManager().buildBoard();
        InputRetriever inputRetriever = new InputManager().buildRetriever();
        ICheckChecker checkChecker = new CheckChecker();

        IChessGame game = new ChessGame(
            playerSilver,
            playerGold,
            board,
            inputRetriever,
            checkChecker
        );

        setObserver(game);
        return game;
    }

    private static void setObserver(IChessGame game) {
        observer = new GameObserver(game);
        game.attach(observer);
    }
}
