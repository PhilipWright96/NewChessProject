package chess.input;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import chess.game.ChessMove;
import chess.pieces.Piece;
import org.junit.Test;

public class ClearPathCheckerTest {

  ChessMove mockMove = mock(ChessMove.class);

  @Test
  public void pathForMoveClear_withAStraightHorizontalMoveWhichIsBlocked_returnsFalse() {
    // Given
    when(mockMove.isStraight()).thenReturn(true);
    when(mockMove.isHorizontal()).thenReturn(true);

    when(mockMove.getMoveFromRow()).thenReturn(6);
    when(mockMove.getMoveFromColumn()).thenReturn(3);
    when(mockMove.getMoveToRow()).thenReturn(6);
    when(mockMove.getMoveToColumn()).thenReturn(5);

    Piece[][] mockBoard = new Piece[8][8];
    mockBoard[4][6] = mock(Piece.class);

    // When
    // Then
    assertEquals(
      false,
      new ClearPathChecker().pathForMoveClear(mockMove, mockBoard)
    );
  }

  @Test
  public void pathForMoveClear_withAStraightVericalMoveWhichIsBlocked_returnsFalse() {
    // Given
    when(mockMove.isStraight()).thenReturn(true);
    when(mockMove.isHorizontal()).thenReturn(false);

    when(mockMove.getMoveFromRow()).thenReturn(6);
    when(mockMove.getMoveFromColumn()).thenReturn(3);
    when(mockMove.getMoveToRow()).thenReturn(4);
    when(mockMove.getMoveToColumn()).thenReturn(3);

    Piece[][] mockBoard = new Piece[8][8];
    mockBoard[3][5] = mock(Piece.class);

    // When
    // Then
    assertEquals(
      false,
      new ClearPathChecker().pathForMoveClear(mockMove, mockBoard)
    );
  }

  @Test
  public void pathForMoveClear_withADiagonalMoveWhichIsBlocked_returnsFalse() {
    // Given
    when(mockMove.isStraight()).thenReturn(false);

    when(mockMove.getMoveFromRow()).thenReturn(6);
    when(mockMove.getMoveFromColumn()).thenReturn(3);
    when(mockMove.getMoveToRow()).thenReturn(4);
    when(mockMove.getMoveToColumn()).thenReturn(5);

    Piece[][] mockBoard = new Piece[8][8];
    mockBoard[4][5] = mock(Piece.class);

    // When
    // Then
    assertEquals(
      false,
      new ClearPathChecker().pathForMoveClear(mockMove, mockBoard)
    );
  }

  @Test
  public void pathForMoveClear_withADiagonalMoveWhichIsNotBlocked_returnsTrue() {
    // Given
    when(mockMove.isStraight()).thenReturn(false);

    when(mockMove.getMoveFromRow()).thenReturn(6);
    when(mockMove.getMoveFromColumn()).thenReturn(3);
    when(mockMove.getMoveToRow()).thenReturn(4);
    when(mockMove.getMoveToColumn()).thenReturn(5);

    Piece[][] mockBoard = new Piece[8][8];

    // When
    // Then
    assertEquals(
      true,
      new ClearPathChecker().pathForMoveClear(mockMove, mockBoard)
    );
  }
}
