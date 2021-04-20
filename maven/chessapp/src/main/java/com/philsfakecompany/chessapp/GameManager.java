package com.philsfakecompany.chessapp.GameManager;

import com.philsfakecompany.chessapp.board.BoardManager.*;
import com.philsfakecompany.chessapp.board.ChessBoard.*;
import com.philsfakecompany.chessapp.game.CheckChecker.*;
import com.philsfakecompany.chessapp.game.ChessGame.*;
import com.philsfakecompany.chessapp.game.GameObserver.*;
import com.philsfakecompany.chessapp.game.ICheckChecker.*;
import com.philsfakecompany.chessapp.game.IChessGame.*;
import com.philsfakecompany.chessapp.input.InputManager.*;
import com.philsfakecompany.chessapp.input.InputRetriever.*;
import com.philsfakecompany.chessapp.player.Player.*;
import com.philsfakecompany.chessapp.util.Teams.*;

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
