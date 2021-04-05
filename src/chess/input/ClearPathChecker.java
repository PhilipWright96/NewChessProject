package chess.input;

import chess.game.ChessMove;
import chess.pieces.IPiece;

public class ClearPathChecker {

  public boolean pathForMoveClear(ChessMove move, IPiece[][] chessBoard) {
    if (move.isStraight()) {
      if (move.isHorizontal()) {
        if (pieceBlockingHorizontalMove(move, chessBoard)) {
          return false;
        }
      } else {
        if (pieceBlockingVerticalMove(move, chessBoard)) {
          return false;
        }
      }
    } else {
      if (pieceBlockingDiagonalMove(move, chessBoard)) {
        return false;
      }
    }
    return true;
  }

  private boolean pieceBlockingHorizontalMove(
    ChessMove move,
    IPiece[][] chessBoard
  ) {
    int moveFromRow = move.getMoveFromRow();
    int moveFromCol = move.getMoveFromColumn();

    int moveToCol = move.getMoveToColumn();

    // If moving right
    if (moveFromCol < moveToCol) {
      for (int col = moveFromCol + 1; col < moveToCol; col++) {
        if (chessBoard[col][moveFromRow] != null) {
          return true;
        }
      }
    }
    // Else moving left
    else {
      for (int col = moveFromCol - 1; col > moveToCol; col--) {
        if (chessBoard[col][moveFromRow] != null) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean pieceBlockingVerticalMove(
    ChessMove move,
    IPiece[][] chessBoard
  ) {
    int moveFromRow = move.getMoveFromRow();
    int moveFromCol = move.getMoveFromColumn();

    int moveToRow = move.getMoveToRow();

    // If moving down
    if (moveFromRow < moveToRow) {
      for (int row = moveFromRow + 1; row < moveToRow; row++) {
        if (chessBoard[moveFromCol][row] != null) {
          return true;
        }
      }
    }
    // Else moving up
    else {
      for (int row = moveFromRow - 1; row > moveToRow; row--) {
        if (chessBoard[moveFromCol][row] != null) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean pieceBlockingDiagonalMove(
    ChessMove move,
    IPiece[][] chessBoard
  ) {
    int moveFromRow = move.getMoveFromRow();
    int moveFromCol = move.getMoveFromColumn();

    int moveToRow = move.getMoveToRow();
    int moveToCol = move.getMoveToColumn();

    // If moving up
    if (moveFromRow > moveToRow) {
      // If moving right
      if (moveFromCol < moveToCol) {
        for (
          int row = moveFromRow - 1, col = moveFromCol + 1;
          row > moveToRow;
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
          row > moveToRow;
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
          row < moveToRow;
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
          row < moveToRow;
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
}
