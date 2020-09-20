package chess.pieces;

import chess.game.ChessMove;

public class Bishop extends Piece {

    public boolean moveValid(ChessMove move){
        if (getRowChangeNum(move) == getColumnChangeNum(move)){
            return true;
        }
        System.out.println("Error: Incorrect move logic for bishop");
        return false;
    }

    private int getRowChangeNum(ChessMove move){
        return Math.abs(move.getMoveFromRow() - move.getMoveToRow());
    }

    private int getColumnChangeNum(ChessMove move){
        return Math.abs(move.getMoveFromColumn() - move.getMoveToColumn());
    }
}