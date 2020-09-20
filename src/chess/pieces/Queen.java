package chess.pieces;

import chess.game.ChessMove;

public class Queen extends Piece {

    public boolean moveValid(ChessMove move){
        if (getRowChangeNum(move) == getColumnChangeNum(move)){
            return true;
        }
        else if (getRowChangeNum(move) > 0 && getColumnChangeNum(move) > 0){
            return false;
        }
        return true;
    }

    private int getRowChangeNum(ChessMove move){
        return Math.abs(move.getMoveFromRow() - move.getMoveToRow());
    }

    private int getColumnChangeNum(ChessMove move){
        return Math.abs(move.getMoveFromColumn() - move.getMoveToColumn());
    }
}