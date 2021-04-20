package com.philsfakecompany.chessapp.game.ICheckChecker;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.input.ClearPathChecker.*;
import com.philsfakecompany.chessapp.player.Player.*;

public interface ICheckChecker {
    public boolean ownKingInCheck(
        ChessMove move,
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    );

    public boolean opposingKingInCheck(
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    );
}
