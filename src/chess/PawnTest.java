package chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void pawnObject_returnsCorrectType(){
        // Given
        Pawn testPawn = new Pawn();
        // When
        char result = testPawn.getType();
        // Then
        assertEquals("Return type of pawn not as expected", 'P', result);
    }

}