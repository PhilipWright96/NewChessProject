package chess.pieces;

import chess.board.IChessBoard;
import chess.game.ChessMove;
import chess.util.Teams;

public class Pawn extends Piece {

    private boolean hasMoved = false;
    private boolean hasTakenPiece = false;

    public boolean moveValid(ChessMove move, IChessBoard chessBoard) {
        if (!isMoving(move)) {
            System.out.println("Pawn not moving anywhere");
            return false;
        }

        if (correctColumn(move, chessBoard) && correctRow(move)) {
            hasMoved = true;
            return true;
        }
        System.out.println("Error: Incorrect move logic for pawn");
        return false;
    }

    private boolean correctColumn(ChessMove move, IChessBoard chessBoard) {
        if (move.getMoveFromColumn() == move.getMoveToColumn()) {
            return true;
        }
        IPiece[][] piecesArray = chessBoard.getPieceArray();

        System.out.println(
            "Move from col is " +
            move.getMoveFromColumn() +
            " Move from row is " +
            move.getMoveFromRow()
        );
        System.out.println(
            "Move to col is " +
            move.getMoveToColumn() +
            " Move to row is " +
            move.getMoveToRow()
        );

        if (getTeam() == Teams.SILVER) {
            System.out.println("Silver moving");
            if (move.getMoveFromColumn() - 1 == move.getMoveToColumn()) {
                System.out.println("Silver moving left");
                IPiece piece =
                    piecesArray[move.getMoveToColumn()][move.getMoveToRow()];
                if (piece != null && piece.getTeam() == Teams.GOLD) {
                    System.out.println("Piece there!");
                    hasTakenPiece = true;
                    return true;
                }
            } else if (
                move.getMoveFromColumn() + 1 == move.getMoveToColumn() &&
                move.getMoveFromRow() - 1 == move.getMoveToRow()
            ) {
                System.out.println("Silver moving right");
                IPiece piece =
                    piecesArray[move.getMoveToColumn()][move.getMoveToRow()];
                if (piece != null && piece.getTeam() == Teams.GOLD) {
                    System.out.println("Piece there!");
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
