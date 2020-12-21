package chess.board;

import org.junit.Test;

import chess.pieces.IPiece;
import chess.pieces.Piece;
import chess.pieces.PieceFactory;
import chess.util.Teams;

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
        IPiece[][] returnedBoard = board.getChessBoard();

        // Then
        verify(mockGui).initializeBoardGUI();

        for (int k = 0; k < returnedBoard.length; k++){
            assertEquals(PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD), returnedBoard[k][1]);
            assertEquals(PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER), returnedBoard[k][returnedBoard.length - 2]);
        }
        verify(mockGui).updateBoardWithPawns();

        for (int l = 0; l < returnedBoard.length; l++){
            assertEquals(PieceFactory.constructPiece(ChessBoard.pieceOrder.get(l), Teams.GOLD), returnedBoard[l][0]);
            assertEquals(PieceFactory.constructPiece(ChessBoard.pieceOrder.get(l), Teams.SILVER), returnedBoard[l][returnedBoard.length - 1]);
        }
        verify(mockGui).updateBoardWithSpecialPieces();
    }

}