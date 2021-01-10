package chess;

import chess.board.BoardManager;
import chess.board.ChessBoard;
import chess.game.ChessGame;
import chess.game.GameObserver;
import chess.game.IChessGame;
import chess.input.InputRetriever;
import chess.input.InputManager;
import chess.player.Player;
import chess.util.Teams;

public class GameManager {
    static GameObserver observer;
    public static void main(String[] args) throws InterruptedException {
        IChessGame game = createChessGame();
        game.playGame();
        if (observer.hasFinished()){
            System.out.println("Game finished");
        }
    }

    private static IChessGame createChessGame(){
        Player playerSilver = new Player("Player 1", Teams.SILVER);
        Player playerGold = new Player("Player 2", Teams.GOLD);
        ChessBoard board = new BoardManager().buildBoard();
        InputRetriever inputRetriever = new InputManager().buildRetriever();

        IChessGame game = new ChessGame(playerSilver, playerGold, board, inputRetriever);

        setObserver(game);
        return game;
    }

    private static void setObserver(IChessGame game) {
        observer = new GameObserver(game);
        game.attach(observer);
    }
}
