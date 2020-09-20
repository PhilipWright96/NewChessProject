package chess.pieces;

import chess.game.ChessMove;

public class King extends Piece {

    public boolean moveValid(ChessMove move){
        if (rowChangeValid(move) && columnChangeValid(move)){
            return true;
        }
        System.out.println("Error: Incorrect move logic for king");
        return false;
    }

    private boolean rowChangeValid(ChessMove move){
        if (getRowChangeNum(move) == 0 || getRowChangeNum(move) == 1){
            return true;
        }
        return false;
    }

    private boolean columnChangeValid(ChessMove move){
        if (getColumnChangeNum(move) == 0 || getColumnChangeNum(move) == 1){
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