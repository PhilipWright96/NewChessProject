package pieces;

import board.*;
import game.*;

public class Bishop extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            return false;
        }

        if (move.getRowChangeNum() == move.getColumnChangeNum()) {
            return true;
        }
        return false;
    }
}
