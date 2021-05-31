package com.philsfakecompany.chessapp.pieces;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.*;
import game.*;
import org.junit.Test;
import pieces.*;

public class RookTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withOnlyColumnMove_returnsTrue() {
        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(2);
        when(mockMove.getMoveToColumn()).thenReturn(4);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        when(mockMove.getMoveFromRow()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(1);
        when(mockMove.getRowChangeNum()).thenReturn(0);

        Rook rook = new Rook();

        // When
        boolean result = rook.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid rook move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withOnlyRowMove_returnsTrue() {
        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(0);

        when(mockMove.getMoveFromRow()).thenReturn(2);
        when(mockMove.getMoveToRow()).thenReturn(4);
        when(mockMove.getRowChangeNum()).thenReturn(2);

        Rook rook = new Rook();

        // When
        boolean result = rook.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid rook move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withBothRowAndColumnMove_returnsFalse() {
        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(2);
        when(mockMove.getColumnChangeNum()).thenReturn(1);

        when(mockMove.getMoveFromRow()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(2);
        when(mockMove.getRowChangeNum()).thenReturn(1);

        Rook rook = new Rook();

        // When
        boolean result = rook.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid rook move marked as valid", false, result);
    }
}
