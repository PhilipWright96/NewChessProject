package pieces;

import board.*;
import game.*;
import util.*;

public class Pawn extends Piece {

    private boolean hasMoved = false;
    private boolean hasTakenPiece = false;

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            return false;
        }

        if (correctColumn(move, chessBoard) && correctRow(move)) {
            hasMoved = true;
            return true;
        }
        return false;
    }

    private boolean correctColumn(ChessMove move, IChessBoard chessBoard) {
        if (move.getMoveFromColumn() == move.getMoveToColumn()) {
            return true;
        }
        IPiece[][] piecesArray = chessBoard.getPieceArray();

        if (getTeam() == Teams.SILVER) {
            if (move.getMoveFromColumn() - 1 == move.getMoveToColumn()) {
                IPiece piece =
                    piecesArray[move.getMoveToColumn()][move.getMoveToRow()];
                if (piece != null && piece.getTeam() == Teams.GOLD) {
                    hasTakenPiece = true;
                    return true;
                }
            } else if (
                move.getMoveFromColumn() + 1 == move.getMoveToColumn() &&
                move.getMoveFromRow() - 1 == move.getMoveToRow()
            ) {
                IPiece piece =
                    piecesArray[move.getMoveToColumn()][move.getMoveToRow()];
                if (piece != null && piece.getTeam() == Teams.GOLD) {
                    hasTakenPiece = true;
                    return true;
                }
            }
        } else {
            if (move.getMoveFromColumn() - 1 == move.getMoveToColumn()) {
                IPiece piece =
                    piecesArray[move.getMoveFromColumn() -
                        1][move.getMoveFromRow() + 1];
                if (piece != null && piece.getTeam() == Teams.GOLD) {
                    hasTakenPiece = true;
                    return true;
                }
            } else if (move.getMoveFromColumn() + 1 == move.getMoveToColumn()) {
                IPiece piece =
                    piecesArray[move.getMoveFromColumn() +
                        1][move.getMoveFromRow() + 1];
                if (piece != null && piece.getTeam() == Teams.GOLD) {
                    hasTakenPiece = true;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean correctRow(ChessMove move) {
        if (getTeam() == Teams.SILVER) {
            if (
                (hasMoved == false || hasTakenPiece == false) &&
                move.getMoveFromRow() - 2 == move.getMoveToRow()
            ) {
                return true;
            } else if (move.getMoveFromRow() - 1 == move.getMoveToRow()) {
                return true;
            }
        } else {
            if (
                hasMoved == false &&
                move.getMoveFromRow() + 2 == move.getMoveToRow()
            ) {
                return true;
            } else if (move.getMoveFromRow() + 1 == move.getMoveToRow()) {
                return true;
            }
        }
        return false;
    }
}
