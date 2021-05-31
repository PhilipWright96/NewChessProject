package com.philsfakecompany.chessapp.pieces;

import static org.junit.Assert.*;

import org.junit.Test;
import pieces.*;
import util.*;

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
