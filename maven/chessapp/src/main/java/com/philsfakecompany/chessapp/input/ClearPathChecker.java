package input;

import game.*;
import java.util.ArrayList;
import pieces.*;

public class ClearPathChecker {

    public boolean pathForMoveClear(
        ChessMove move,
        IPiece[][] chessBoard,
        boolean checkMoveTargetSpace
    ) {
        if (move.isStraight()) {
            if (move.isHorizontal()) {
                if (
                    pieceBlockingHorizontalMove(
                        move,
                        chessBoard,
                        checkMoveTargetSpace
                    )
                ) {
                    return false;
                }
            } else {
                if (
                    pieceBlockingVerticalMove(
                        move,
                        chessBoard,
                        checkMoveTargetSpace
                    )
                ) {
                    return false;
                }
            }
        } else {
            if (
                pieceBlockingDiagonalMove(
                    move,
                    chessBoard,
                    checkMoveTargetSpace
                )
            ) {
                return false;
            }
        }
        return true;
    }

    private boolean pieceBlockingHorizontalMove(
        ChessMove move,
        IPiece[][] chessBoard,
        boolean checkMoveTargetSpace
    ) {
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToCol = move.getMoveToColumn();

        int augmentVal = checkMoveTargetSpace == true ? 1 : 0;

        // If moving right
        if (moveFromCol < moveToCol) {
            for (
                int col = moveFromCol + 1;
                col < moveToCol + augmentVal;
                col++
            ) {
                if (chessBoard[col][moveFromRow] != null) {
                    return true;
                }
            }
        }
        // Else moving left
        else {
            for (
                int col = moveFromCol - 1;
                col > moveToCol - augmentVal;
                col--
            ) {
                if (chessBoard[col][moveFromRow] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pieceBlockingVerticalMove(
        ChessMove move,
        IPiece[][] chessBoard,
        boolean checkMoveTargetSpace
    ) {
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToRow = move.getMoveToRow();

        int augmentVal = checkMoveTargetSpace == true ? 1 : 0;

        // If moving down
        if (moveFromRow < moveToRow) {
            for (
                int row = moveFromRow + 1;
                row < moveToRow + augmentVal;
                row++
            ) {
                if (chessBoard[moveFromCol][row] != null) {
                    return true;
                }
            }
        }
        // Else moving up
        else {
            for (
                int row = moveFromRow - 1;
                row > moveToRow - augmentVal;
                row--
            ) {
                if (chessBoard[moveFromCol][row] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pieceBlockingDiagonalMove(
        ChessMove move,
        IPiece[][] chessBoard,
        boolean checkMoveTargetSpace
    ) {
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToRow = move.getMoveToRow();
        int moveToCol = move.getMoveToColumn();

        int augmentVal = checkMoveTargetSpace == true ? 1 : 0;

        // If moving up
        if (moveFromRow > moveToRow) {
            // If moving right
            if (moveFromCol < moveToCol) {
                for (
                    int row = moveFromRow - 1, col = moveFromCol + 1;
                    row > moveToRow - augmentVal;
                    row--, col++
                ) {
                    if (chessBoard[col][row] != null) {
                        return true;
                    }
                }
            }
            // Else moving left
            else {
                for (
                    int row = moveFromRow - 1, col = moveFromCol - 1;
                    row > moveToRow - augmentVal;
                    row--, col--
                ) {
                    if (chessBoard[col][row] != null) {
                        return true;
                    }
                }
            }
        }
        // Else moving down
        else {
            // If moving right
            if (moveFromCol < moveToCol) {
                for (
                    int row = moveFromRow + 1, col = moveFromCol + 1;
                    row < moveToRow + augmentVal;
                    row++, col++
                ) {
                    if (chessBoard[col][row] != null) {
                        return true;
                    }
                }
            }
            // Else moving left
            else {
                for (
                    int row = moveFromRow + 1, col = moveFromCol - 1;
                    row < moveToRow + augmentVal;
                    row++, col--
                ) {
                    if (chessBoard[col][row] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Coordinates> getCoordinatesBetweenMove(
        ChessMove move,
        boolean checkMoveTargetSpace
    ) {
        if (move.isStraight()) {
            if (move.isHorizontal()) {
                return getCoordinatesForHorizontalMove(
                    move,
                    checkMoveTargetSpace
                );
            } else {
                return getCoordinatesForVerticalMove(
                    move,
                    checkMoveTargetSpace
                );
            }
        } else {
            return getCoordinatesForDiagonalMove(move, checkMoveTargetSpace);
        }
    }

    private ArrayList<Coordinates> getCoordinatesForHorizontalMove(
        ChessMove move,
        boolean checkMoveTargetSpace
    ) {
        ArrayList<Coordinates> coordinatesBetweenMove = new ArrayList<Coordinates>();
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToCol = move.getMoveToColumn();
        int augmentVal = checkMoveTargetSpace == true ? 1 : 0;
        if (moveFromCol < moveToCol) {
            for (
                int col = moveFromCol + 0;
                col < moveToCol + augmentVal;
                col++
            ) {
                coordinatesBetweenMove.add(new Coordinates(moveFromRow, col));
            }
        }
        // Else moving left
        else {
            for (
                int col = moveFromCol - 0;
                col > moveToCol - augmentVal;
                col--
            ) {
                coordinatesBetweenMove.add(new Coordinates(moveFromRow, col));
            }
        }
        return coordinatesBetweenMove;
    }

    private ArrayList<Coordinates> getCoordinatesForVerticalMove(
        ChessMove move,
        boolean checkMoveTargetSpace
    ) {
        ArrayList<Coordinates> coordinatesBetweenMove = new ArrayList<Coordinates>();
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToRow = move.getMoveToRow();

        int augmentVal = checkMoveTargetSpace == true ? 1 : 0;

        // If moving down
        if (moveFromRow < moveToRow) {
            for (
                int row = moveFromRow + 1;
                row < moveToRow + augmentVal;
                row++
            ) {
                coordinatesBetweenMove.add(new Coordinates(row, moveFromCol));
            }
        }
        // Else moving up
        else {
            for (
                int row = moveFromRow - 1;
                row > moveToRow - augmentVal;
                row--
            ) {
                coordinatesBetweenMove.add(new Coordinates(row, moveFromCol));
            }
        }
        return coordinatesBetweenMove;
    }

    private ArrayList<Coordinates> getCoordinatesForDiagonalMove(
        ChessMove move,
        boolean checkMoveTargetSpace
    ) {
        ArrayList<Coordinates> coordinatesBetweenMove = new ArrayList<Coordinates>();
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToRow = move.getMoveToRow();
        int moveToCol = move.getMoveToColumn();

        int augmentVal = checkMoveTargetSpace == true ? 1 : 0;

        // If moving up
        if (moveFromRow > moveToRow) {
            // If moving right
            if (moveFromCol < moveToCol) {
                for (
                    int row = moveFromRow - 1, col = moveFromCol + 1;
                    row >= moveToRow - augmentVal;
                    row--, col++
                ) {
                    coordinatesBetweenMove.add(new Coordinates(row, col));
                }
            }
            // Else moving left
            else {
                for (
                    int row = moveFromRow - 1, col = moveFromCol - 1;
                    row >= moveToRow - augmentVal;
                    row--, col--
                ) {
                    coordinatesBetweenMove.add(new Coordinates(row, col));
                }
            }
        }
        // Else moving down
        else {
            // If moving right
            if (moveFromCol < moveToCol) {
                for (
                    int row = moveFromRow + 1, col = moveFromCol + 1;
                    row <= moveToRow + augmentVal;
                    row++, col++
                ) {
                    coordinatesBetweenMove.add(new Coordinates(row, col));
                }
            }
            // Else moving left
            else {
                for (
                    int row = moveFromRow + 1, col = moveFromCol - 1;
                    row <= moveToRow + augmentVal;
                    row++, col--
                ) {
                    coordinatesBetweenMove.add(new Coordinates(row, col));
                }
            }
        }
        return coordinatesBetweenMove;
    }
}
