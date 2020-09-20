package chess.pieces;

import chess.game.ChessMove;

public class Bishop extends Piece {

    public boolean moveValid(ChessMove move){
        if (move.getRowChangeNum() == move.getColumnChangeNum()){
            return true;
        }
        System.out.println("Error: Incorrect move logic for bishop");
        return false;
    }
}