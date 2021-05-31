package com.philsfakecompany.chessapp.pieces;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.*;
import game.*;
import org.junit.Test;
import pieces.*;

public class QueenTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withValidDiagonalMove_returnsTrue() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(2);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        Queen queen = new Queen();

        // When
        boolean result = queen.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid queen move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withInvalidUnequalRowColumnMove_returnsFalse() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        Queen queen = new Queen();

        // When
        boolean result = queen.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid queen move marked as valid", false, result);
    }

    @Test
    public void moveValid_withValidHorizontalMove_returnsTrue() {
        // Given
        when(mockMove.getRowChangeNum()).thenReturn(5);
        when(mockMove.getColumnChangeNum()).thenReturn(0);

        Queen queen = new Queen();

        // When
        boolean result = queen.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid queen move marked as invalid", true, result);
    }
}
