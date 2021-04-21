package pieces;

import board.*;
import game.*;
import pieces.*;

public class King extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            System.out.println("King not moving anywhere");
            return false;
        }

        if (rowChangeValid(move) && columnChangeValid(move)) {
            return true;
        }
        System.out.println("Error: Incorrect move logic for king");
        return false;
    }

    private boolean rowChangeValid(ChessMove move) {
        if (move.getRowChangeNum() == 0 || move.getRowChangeNum() == 1) {
            return true;
        }
        return false;
    }

    private boolean columnChangeValid(ChessMove move) {
        if (move.getColumnChangeNum() == 0 || move.getColumnChangeNum() == 1) {
            return true;
        }
        return false;
    }
}
