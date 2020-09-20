package chess.pieces;

import chess.game.ChessMove;

public class Knight extends Piece {

    public boolean moveValid(ChessMove move){
        if (getRowChangeNum(move) == 1 && getColumnChangeNum(move) == 2){
            return true;
        }
        else if (getRowChangeNum(move) == 2 && getColumnChangeNum(move) == 1){
            return true;
        }
        return false;
    }

    private int getRowChangeNum(ChessMove move){
        return Math.abs(move.getMoveFromRow() - move.getMoveToRow());
    }

    private int getColumnChangeNum(ChessMove move){
        return Math.abs(move.getMoveFromColumn() - move.getMoveToColumn());
    }
}