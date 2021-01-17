package chess.pieces;

import org.junit.Test;

import chess.board.ChessBoard;
import chess.game.ChessMove;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class BishopTest {
    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withDiagonalMovePassed_returnsTrue(){

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
    public void moveValid_withNonDiagonalMovePassed_returnsFalse(){
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