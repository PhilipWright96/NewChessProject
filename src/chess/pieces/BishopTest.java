package chess.pieces;

import org.junit.Test;

import chess.board.ChessBoard;
import chess.board.ChessBoardGUI;
import chess.game.ChessMove;
import static org.junit.Assert.*;

public class BishopTest {

    @Test
    public void moveValid_withDiagonalMovePassed_returnsTrue(){

        // Given
        ChessMove diagonalMove = new ChessMove("a1-b2");
        Bishop bishop = new Bishop();

        // When
        boolean result = bishop.moveValid(diagonalMove, new ChessBoard(new ChessBoardGUI()));
        
        // Then
        assertEquals("Valid bishop move marked as invalid", true, result);
    }

    @Test
    public void moveValid_withVerticalMovePassed_returnsFalse(){
        // Given
        ChessMove diagonalMove = new ChessMove("a1-a2");
        Bishop bishop = new Bishop();

        // When
        boolean result = bishop.moveValid(diagonalMove, new ChessBoard(new ChessBoardGUI()));

        // Then
        assertEquals("Invalid bishop move marked as valid", false, result);
    }
}