package com.philsfakecompany.chessapp.game.IChessGame;

import com.philsfakecompany.chessapp.game.GameObserver.*;

public interface IChessGame {
    public void attach(GameObserver observer);

    public void playGame() throws InterruptedException;
}
