package test.java.com.philsfakecompany.chessapp.pieces.PieceTest;

import static org.junit.Assert.*;

import com.philsfakecompany.chessapp.board.ChessBoard.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;
import com.philsfakecompany.chessapp.pieces.PieceFactory.*;
import com.philsfakecompany.chessapp.util.Teams.*;
import org.junit.Test;

public class PieceTest {

    @Test
    public void equals_withTwoPiecesWithTheSameAttributtes_returnsTrue() {
        // Given
        Piece piece1 = PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.SILVER
        );
        Piece piece2 = PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.SILVER
        );

        // When
        // Then
        assertEquals("Equal pieces not registered as equal", piece1, piece2);
        assertEquals(
            "Different hash codes produced for same pieces",
            piece1.hashCode(),
            piece2.hashCode()
        );
    }
}
