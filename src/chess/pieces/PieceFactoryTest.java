package chess.pieces;

import static org.junit.Assert.*;

import chess.pieces.Piece.Types;
import chess.util.Teams;
import org.junit.Test;

public class PieceFactoryTest {

  @Test
  public void constructPiece_returnsPieceOfCorrectTypeAndTeam() {
    // Given
    // When
    Piece result = PieceFactory.constructPiece(Types.PAWN, Teams.GOLD);

    // Then
    assertEquals(Types.PAWN, result.getType());
    assertEquals(Teams.GOLD, result.getTeam());
  }
}
