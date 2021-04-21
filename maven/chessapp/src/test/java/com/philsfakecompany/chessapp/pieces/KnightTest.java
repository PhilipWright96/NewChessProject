import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.*;
import game.*;
import org.junit.Test;
import pieces.*;

public class KnightTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withInvalidColumnChangeMove_returnsFalse() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(3);

        Knight knight = new Knight();

        // When
        boolean result = knight.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid knight move marked as valid", false, result);
    }

    @Test
    public void moveValid_withInvalidRowChangeMove_returnsFalse() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(3);
        when(mockMove.getColumnChangeNum()).thenReturn(1);

        Knight knight = new Knight();

        // When
        boolean result = knight.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid knight move marked as valid", false, result);
    }

    @Test
    public void moveValid_withValidMove_returnsTrue() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        Knight knight = new Knight();

        // When
        boolean result = knight.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid knight move marked as invalid", true, result);
    }
}
