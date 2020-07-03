package chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnAWhitePawn(){
        // Given
        Pawn testPawn = new Pawn(Piece.Teams.WHITE);
        // When
        char result = testPawn.getType();
        // Then
        assertEquals("Return type of pawn not as expected", 'P', result);
    }

    @Test
    public void pawnObject_returnsCorrectType_whenCalledOnABlackPawn(){
        // Given
        Pawn testPawn = new Pawn(Piece.Teams.BLACK);
        // When
        char result = testPawn.getType();
        // Then
        assertEquals("Return type of pawn not as expected", 'p', result);
    }

}