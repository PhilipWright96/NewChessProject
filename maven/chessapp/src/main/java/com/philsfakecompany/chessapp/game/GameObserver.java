package com.philsfakecompany.chessapp.game.GameObserver;

import com.philsfakecompany.chessapp.game.ChessGame.*;
import com.philsfakecompany.chessapp.game.IChessGame.*;

public class GameObserver {

    IChessGame game;
    public boolean hasFinished = false;

    public GameObserver(IChessGame game) {
        this.game = game;
        this.game.attach(this);
    }

    public void update() {
        hasFinished = true;
    }

    public boolean hasFinished() {
        return hasFinished;
    }
}
