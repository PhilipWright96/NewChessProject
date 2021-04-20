package com.philsfakecompany.chessapp.pieces.Bishop;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;

public class Bishop extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            System.out.println("Bishop not moving anywhere");
            return false;
        }

        if (move.getRowChangeNum() == move.getColumnChangeNum()) {
            return true;
        }
        System.out.println("Error: Incorrect move logic for bishop");
        return false;
    }
}
