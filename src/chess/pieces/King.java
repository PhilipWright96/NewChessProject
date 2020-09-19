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
        if (move.getMoveFromRow() == move.getMoveToRow()){
            return true;
        }
        if (move.getMoveFromRow() + 1 == move.getMoveToRow()){
            return true;
        }
        if (move.getMoveFromRow() - 1 == move.getMoveToRow()){
            return true;
        }
        return false;
    }

    private boolean columnChangeValid(ChessMove move){
        if (move.getMoveFromColumn() == move.getMoveToColumn()){
            return true;
        }
        if (move.getMoveFromColumn() + 1 == move.getMoveToColumn()){
            return true;
        }
        if (move.getMoveFromColumn() - 1 == move.getMoveToColumn()){
            return true;
        }
        return false;
    }
}