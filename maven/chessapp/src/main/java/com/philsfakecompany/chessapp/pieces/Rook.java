package com.philsfakecompany.chessapp.pieces.Rook;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;

public class Rook extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            System.out.println("Rook not moving anywhere");
            return false;
        }

        if (columnMoved(move) && rowMoved(move)) {
            System.out.println("Error: Incorrect move logic for rook");
            return false;
        }
        return true;
    }

    private boolean columnMoved(ChessMove move) {
        if (move.getMoveFromColumn() != move.getMoveToColumn()) {
            return true;
        }
        return false;
    }

    private boolean rowMoved(ChessMove move) {
        if (move.getMoveFromRow() != move.getMoveToRow()) {
            return true;
        }
        return false;
    }
}
