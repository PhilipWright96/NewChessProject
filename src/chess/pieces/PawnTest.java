package chess.pieces;

import org.junit.Test;

import chess.board.ChessBoard;
import chess.game.ChessMove;
import chess.pieces.Piece.Types;
import chess.util.Teams;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PawnTest {

    ChessBoard mockBoard = mock(ChessBoard.class);
    ChessMove mockMove = mock(ChessMove.class);

    @Test
    public void moveValid_withInvalidColumnChangeMove_returnsFalse(){

        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(2);
        when(mockMove.getMoveToColumn()).thenReturn(4);

        Pawn pawn = (Pawn) PieceFactory.constructPiece(Types.PAWN, Teams.SILVER);

        // When
        boolean result = pawn.moveValid(mockMove, mockBoard);
        
        // Then
        assertEquals("Invalid pawn move marked as valid", false, result);
    }

    @Test
    public void moveValid_withInvalidRowChangeMove_returnsFalse(){

        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(1);

        when(mockMove.getMoveFromRow()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(4);

        Pawn pawn = (Pawn) PieceFactory.constructPiece(Types.PAWN, Teams.SILVER);

        // When
        boolean result = pawn.moveValid(mockMove, mockBoard);
        
        // Then
        assertEquals("Invalid pawn move marked as valid", false, result);
    }

    @Test
    public void moveValid_withValidMove_returnsTrue(){

        // Given
        when(mockMove.getMoveFromColumn()).thenReturn(1);
        when(mockMove.getMoveToColumn()).thenReturn(1);

        when(mockMove.getMoveFromRow()).thenReturn(3);
        when(mockMove.getMoveToRow()).thenReturn(1);

        Pawn pawn = (Pawn) PieceFactory.constructPiece(Types.PAWN, Teams.SILVER);

        // When
        boolean result = pawn.moveValid(mockMove, mockBoard);
        
        // Then
        assertEquals("Valid pawn move marked as invalid", true, result);
    }

}