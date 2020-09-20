package chess.pieces;

import chess.game.ChessMove;

public class Queen extends Piece {

    public boolean moveValid(ChessMove move){
        if (move.getRowChangeNum() == move.getColumnChangeNum()){
            return true;
        }
        else if (move.getRowChangeNum() > 0 && move.getColumnChangeNum() > 0){
            return false;
        }
        return true;
    }
}