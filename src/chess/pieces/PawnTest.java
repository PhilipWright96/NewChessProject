package chess.pieces;

import org.junit.Test;

import chess.util.Teams;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnAWhitePawn(){
        // Given
        Piece testPawn = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD);
        // When
        Teams result = testPawn.getTeam();
        // Then
        assertEquals("Return type of pawn not as expected", Teams.GOLD, result);
    }

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnABlackPawn(){
        // Given
        Piece testPawn = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        // When
        Teams result = testPawn.getTeam();
        // Then
        assertEquals("Return type of pawn not as expected", Teams.SILVER, result);
    }

}