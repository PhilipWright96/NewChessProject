package pieces;

import board.*;
import game.*;
import pieces.*;

public class Bishop extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            System.out.println("Bishop not moving anywhere");
            return false;
        }

        if (move.getRowChangeNum() == move.getColumnChangeNum()) {
            return true;
        }
        System.out.println("Error: Incorrect move logic for bishop");
        return false;
    }
}
