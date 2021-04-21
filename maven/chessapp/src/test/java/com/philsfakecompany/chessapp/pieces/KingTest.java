import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.*;
import game.*;
import org.junit.Test;
import pieces.*;

public class KingTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withInvalidRowChangeMove_returnsFalse() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(2);

        King king = new King();

        // When
        boolean result = king.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid king move marked as valid", false, result);
    }

    @Test
    public void moveValid_withInvalidColumnChangeMove_returnsFalse() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        King king = new King();

        // When
        boolean result = king.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid king move marked as valid", false, result);
    }

    @Test
    public void moveValid_withValidMove_returnsTrue() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);

        when(mockMove.getColumnChangeNum()).thenReturn(1);

        King king = new King();

        // When
        boolean result = king.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid king move marked as invalid", true, result);
    }
}
