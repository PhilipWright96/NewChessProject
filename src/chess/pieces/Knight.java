package chess.pieces;

import chess.game.ChessMove;

public class Knight extends Piece {

    public boolean moveValid(ChessMove move){
        if (move.getRowChangeNum() == 1 && move.getColumnChangeNum() == 2){
            return true;
        }
        else if (move.getRowChangeNum() == 2 && move.getColumnChangeNum() == 1){
            return true;
        }
        return false;
    }
}