package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnAWhitePawn(){
        // Given
        Pawn testPawn = new Pawn(Piece.Teams.GOLD);
        // When
        Piece.Teams result = testPawn.getTeam();
        // Then
        assertEquals("Return type of pawn not as expected", Piece.Teams.GOLD, result);
    }

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnABlackPawn(){
        // Given
        Pawn testPawn = new Pawn(Piece.Teams.SILVER);
        // When
        Piece.Teams result = testPawn.getTeam();
        // Then
        assertEquals("Return type of pawn not as expected", Piece.Teams.SILVER, result);
    }

}