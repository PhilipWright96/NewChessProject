package chess.pieces;

import org.junit.Test;

import chess.pieces.Piece.Types;
import chess.util.Teams;

import static org.junit.Assert.*;

public class PieceFactoryTest {

    @Test
    public void constructPiece_returnsPieceOfCorrectTypeAndTeam(){

        // Given
        // When
        Piece result = PieceFactory.constructPiece(Types.PAWN, Teams.GOLD);

        // Then
        assertEquals(Types.PAWN, result.getType());
        assertEquals(Teams.GOLD, result.getTeam());
    }
    
}
