package chess.pieces;

import org.junit.Test;

import chess.board.ChessBoard;
import chess.game.ChessMove;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RookTest {
    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withOnlyColumnMove_returnsTrue(){

        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(2);
        when(mockMove.getMoveToColumn()).thenReturn(4);
        when(mockMove.getMoveFromRow()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(1);

        Rook rook = new Rook();

        // When
        boolean result = rook.moveValid(mockMove, mockBoard);
        
        // Then
        assertEquals("Valid rook move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withOnlyRowMove_returnsTrue(){

        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(1);
        when(mockMove.getMoveFromRow()).thenReturn(2);
        when(mockMove.getMoveToRow()).thenReturn(4);

        Rook rook = new Rook();

        // When
        boolean result = rook.moveValid(mockMove, mockBoard);
        
        // Then
        assertEquals("Valid rook move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withBothRowAndColumnMove_returnsFalse(){

        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(2);
        when(mockMove.getMoveFromRow()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(2);

        Rook rook = new Rook();

        // When
        boolean result = rook.moveValid(mockMove, mockBoard);
        
        // Then
        assertEquals("Invalid rook move marked as valid", false, result);
    }
    
}
