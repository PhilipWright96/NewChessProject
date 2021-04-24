package pieces;

import board.*;
import game.*;

public class Queen extends Piece {

    public final boolean moveValid(
        final ChessMove move,
        final IChessBoard chessBoard
    ) {
        if (!isMoving(move)) {
            System.out.println("Queen not moving anywhere");
            return false;
        }
        if (move.getRowChangeNum() == move.getColumnChangeNum()) {
            return true;
        } else if (
            move.getRowChangeNum() > 0 && move.getColumnChangeNum() > 0
        ) {
            return false;
        }
        return true;
    }
}
