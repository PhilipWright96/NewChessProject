package chess.board;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ChessBoardTest {

    IChessBoardGUI mockGui = mock(ChessBoardGUI.class);

    @Test
    public void initializeChessBoard_initializesGuiAndSetsBoard(){

        // Given
        ChessBoard board = new ChessBoard(mockGui);

        // When
        board.initializeChessBoard();

        // Then
        verify(mockGui).initializeBoardGUI();
        verify(mockGui).updateBoardWithPawns();
        verify(mockGui).updateBoardWithSpecialPieces();
    }

}