package chess.pieces;

import chess.game.ChessMove;

public class Rook extends Piece {

    public boolean moveValid(ChessMove move){
        if (columnMoved(move) && rowMoved(move)){
            System.out.println("Error: Incorrect move logic for rook");
            return false;
        }
        return true;
    }

    private boolean columnMoved(ChessMove move){
        if (move.getMoveFromColumn() != move.getMoveToColumn()){
            return true;
        }
        return false;
    }

    private boolean rowMoved(ChessMove move){
        if (move.getMoveFromRow() != move.getMoveToRow()){
            return true;
        }
        return false;
    }
}