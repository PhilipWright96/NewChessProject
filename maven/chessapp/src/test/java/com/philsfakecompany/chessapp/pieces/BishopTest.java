package test.java.com.philsfakecompany.chessapp.pieces.BishopTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.philsfakecompany.chessapp.board.ChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.Bishop.*;
import org.junit.Test;

public class BishopTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withDiagonalMovePassed_returnsTrue() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(1);

        Bishop bishop = new Bishop();

        // When
        boolean result = bishop.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid bishop move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withNonDiagonalMovePassed_returnsFalse() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        Bishop bishop = new Bishop();

        // When
        boolean result = bishop.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid bishop move marked as valid", false, result);
    }
}
