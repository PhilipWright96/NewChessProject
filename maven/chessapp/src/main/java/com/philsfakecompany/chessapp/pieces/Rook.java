package pieces;

import board.*;
import game.*;

public class Rook extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            return false;
        }

        if (columnMoved(move) && rowMoved(move)) {
            return false;
        }
        return true;
    }

    private boolean columnMoved(ChessMove move) {
        if (move.getMoveFromColumn() != move.getMoveToColumn()) {
            return true;
        }
        return false;
    }

    private boolean rowMoved(ChessMove move) {
        if (move.getMoveFromRow() != move.getMoveToRow()) {
            return true;
        }
        return false;
    }
}
