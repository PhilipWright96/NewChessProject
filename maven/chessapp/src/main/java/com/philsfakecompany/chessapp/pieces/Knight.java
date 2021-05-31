package pieces;

import board.*;
import game.*;

public class Knight extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard board) {
        if (!isMoving(move)) {
            return false;
        }

        if (move.getRowChangeNum() == 1 && move.getColumnChangeNum() == 2) {
            return true;
        } else if (
            move.getRowChangeNum() == 2 && move.getColumnChangeNum() == 1
        ) {
            return true;
        }
        return false;
    }
}
