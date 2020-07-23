package chess.pieces;

import org.junit.Test;
import chess.pieces.Piece;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnAWhitePawn(){
        // Given
        Piece testPawn = PieceFactory.constructPiece(Piece.Types.PAWN, Piece.Teams.GOLD);
        // When
        Piece.Teams result = testPawn.getTeam();
        // Then
        assertEquals("Return type of pawn not as expected", Piece.Teams.GOLD, result);
    }

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnABlackPawn(){
        // Given
        Piece testPawn = PieceFactory.constructPiece(Piece.Types.PAWN, Piece.Teams.SILVER);
        // When
        Piece.Teams result = testPawn.getTeam();
        // Then
        assertEquals("Return type of pawn not as expected", Piece.Teams.SILVER, result);
    }

}