package chess.pieces;

import org.junit.Test;

import chess.pieces.Piece.Types;
import chess.util.Teams;

import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void equals_withTwoPiecesWithTheSameAttributtes_returnsTrue(){

        // Given
        Piece piece1 = PieceFactory.constructPiece(Types.PAWN, Teams.SILVER);
        Piece piece2 = PieceFactory.constructPiece(Types.PAWN, Teams.SILVER);

        // When        
        // Then
        assertEquals("Equal pieces not registered as equal", piece1, piece2);
        assertEquals("Different hash codes produced for same pieces", piece1.hashCode(), piece2.hashCode());

    }
    
}
