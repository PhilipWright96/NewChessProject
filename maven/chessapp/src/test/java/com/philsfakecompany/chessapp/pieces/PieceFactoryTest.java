package test.java.com.philsfakecompany.chessapp.pieces.PieceFactoryTest;

import static org.junit.Assert.*;

import com.philsfakecompany.chessapp.pieces.Piece.*;
import com.philsfakecompany.chessapp.pieces.PieceFactory.*;
import com.philsfakecompany.chessapp.util.Teams.*;
import org.junit.Test;

public class PieceFactoryTest {

    @Test
    public void constructPiece_returnsPieceOfCorrectTypeAndTeam() {
        // Given
        // When
        Piece result = PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.GOLD
        );

        // Then
        assertEquals(Piece.Types.PAWN, result.getType());
        assertEquals(Teams.GOLD, result.getTeam());
    }
}
