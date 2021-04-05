package chess.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessMoveTest {

    @Test
    public void constructor_givenCorrectInputForMove_setsCorrectRowAndColumnMovementNumbers() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("c8-e6");

        // Then
        assertEquals(2, chessMove.getMoveFromColumn());
        assertEquals(7, chessMove.getMoveFromRow());
        assertEquals(4, chessMove.getMoveToColumn());
        assertEquals(5, chessMove.getMoveToRow());
    }

    @Test
    public void getRowChangeNum_returnsCorrectDifferenceOfRows() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("c8-e6");

        // Then
        assertEquals(2, chessMove.getRowChangeNum());
    }

    @Test
    public void getColumnChangeNum_returnsCorrectDifferenceOfRows() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("c8-e6");

        // Then
        assertEquals(2, chessMove.getColumnChangeNum());
    }

    @Test
    public void moveToNewSquare_withMoveForRowChange_returnsTrue() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-d5");

        // Then
        assertEquals(true, chessMove.isMovingToNewSquare());
    }

    @Test
    public void isMovingToNewSquare_withMoveForColumnChange_returnsTrue() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-e7");

        // Then
        assertEquals(true, chessMove.isMovingToNewSquare());
    }

    @Test
    public void isMovingToNewSquare_withMoveToSameLocation_returnsFalse() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-d7");

        // Then
        assertEquals(false, chessMove.isMovingToNewSquare());
    }

    @Test
    public void isStraight_withHorizontalMove_returnsTrue() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-e7");

        // Then
        assertEquals(true, chessMove.isStraight());
    }

    @Test
    public void isStraight_withVerticalMove_returnsTrue() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-d5");

        // Then
        assertEquals(true, chessMove.isStraight());
    }

    @Test
    public void isStraight_withDiagonalMove_returnsFalse() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-f5");

        // Then
        assertEquals(false, chessMove.isStraight());
    }

    @Test
    public void isHorizontal_withHorizontalMove_returnsTrue() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-e7");

        // Then
        assertEquals(true, chessMove.isHorizontal());
    }

    @Test
    public void isHorizontal_withVerticalMove_returnsFalse() {
        // Given
        // When
        ChessMove chessMove = new ChessMove("d7-d6");

        // Then
        assertEquals(false, chessMove.isHorizontal());
    }
}
