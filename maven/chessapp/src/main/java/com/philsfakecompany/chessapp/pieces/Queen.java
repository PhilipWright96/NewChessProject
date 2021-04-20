package com.philsfakecompany.chessapp.pieces.Queen;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;

public class Queen extends Piece {

    public final boolean moveValid(
        final ChessMove move,
        final IChessBoard chessBoard
    ) {
        if (!isMoving(move)) {
            System.out.println("Queen not moving anywhere");
            return false;
        }
        if (move.getRowChangeNum() == move.getColumnChangeNum()) {
            return true;
        } else if (
            move.getRowChangeNum() > 0 && move.getColumnChangeNum() > 0
        ) {
            return false;
        }
        return true;
    }
}
