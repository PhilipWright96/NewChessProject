package com.philsfakecompany.chessapp.pieces;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.*;
import game.*;
import org.junit.Test;
import pieces.*;
import util.*;

public class PawnTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withInvalidColumnChangeMove_returnsFalse() {
        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(2);
        when(mockMove.getMoveToColumn()).thenReturn(4);
        when(mockMove.getColumnChangeNum()).thenReturn(2);

        Pawn pawn = (Pawn) PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.SILVER
        );

        // When
        boolean result = pawn.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid pawn move marked as valid", false, result);
    }

    @Test
    public void moveValid_withInvalidRowChangeMove_returnsFalse() {
        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(0);

        when(mockMove.getMoveFromRow()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(4);
        when(mockMove.getRowChangeNum()).thenReturn(3);

        Pawn pawn = (Pawn) PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.SILVER
        );

        // When
        boolean result = pawn.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Invalid pawn move marked as valid", false, result);
    }

    @Test
    public void moveValid_withValidMove_returnsTrue() {
        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(1);
        when(mockMove.getColumnChangeNum()).thenReturn(0);

        when(mockMove.getMoveFromRow()).thenReturn(3);
        when(mockMove.getMoveToRow()).thenReturn(1);
        when(mockMove.getRowChangeNum()).thenReturn(2);

        Pawn pawn = (Pawn) PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.SILVER
        );

        // When
        boolean result = pawn.moveValid(mockMove, mockBoard);

        // Then
        assertEquals("Valid pawn move marked as invalid", true, result);
    }
}
