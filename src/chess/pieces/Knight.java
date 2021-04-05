package chess.pieces;

import chess.board.IChessBoard;
import chess.game.ChessMove;

public class Knight extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard board){
        if (!isMoving(move)){
            System.out.println("Knight not moving anywhere");
            return false;
        }

        if (move.getRowChangeNum() == 1 && move.getColumnChangeNum() == 2){
            return true;
        }
        else if (move.getRowChangeNum() == 2 && move.getColumnChangeNum() == 1){
            return true;
        }
        System.out.println("Incorrect move logic for knight");
        return false;
    }
}