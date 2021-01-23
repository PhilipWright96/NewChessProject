package chess.board;

import org.junit.Test;

import chess.game.ChessMove;
import chess.pieces.IPiece;
import chess.pieces.Piece;
import chess.pieces.PieceFactory;
import chess.util.Teams;
import chess.board.GUI.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ChessBoardTest {

    IChessBoardGUI mockGui = mock(ChessBoardGUI.class);
    ChessMove mockMove = mock(ChessMove.class);
    ChessBoard board = new ChessBoard(mockGui);

    @Test
    public void initializeChessBoard_initializesGuiAndSetsBoard(){

        // Given
        // When
        board.initializeChessBoard();
        IPiece[][] returnedBoard = board.getPieceArray();

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

    @Test
    public void getPieceBeingMoved_withMovePassedIn_returnsCorrectPiece(){

        // Given
        IPiece pieceOnBoard = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        board.getPieceArray()[3][6] = pieceOnBoard;

        when(mockMove.getMoveFromColumn()).thenReturn(3);
        when(mockMove.getMoveFromRow()).thenReturn(6);

        // When
        IPiece returnedPiece = board.getPieceBeingMoved(mockMove);

        // Then
        verify(mockMove).getMoveFromColumn();
        verify(mockMove).getMoveFromRow();
        assertEquals(pieceOnBoard, returnedPiece);
    }

    @Test
    public void getPieceBeingTaken_withMovePassedIn_returnsCorrectPiece(){

        // Given
        IPiece pieceOnBoard = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        board.getPieceArray()[3][5] = pieceOnBoard;

        when(mockMove.getMoveToColumn()).thenReturn(3);
        when(mockMove.getMoveToRow()).thenReturn(5);

        // When
        IPiece returnedPiece = board.getPieceBeingTaken(mockMove);

        // Then
        verify(mockMove).getMoveToColumn();
        verify(mockMove).getMoveToRow();
        assertEquals(pieceOnBoard, returnedPiece);
    }

    @Test
    public void movePiece_withMovePassedIn_correctlyUpdatesBoardArrayAndCallsGui(){

        // Given
        IPiece pieceBeingMoved = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        board.getPieceArray()[3][6] = pieceBeingMoved;

        when(mockMove.getMoveFromColumn()).thenReturn(3);
        when(mockMove.getMoveFromRow()).thenReturn(6);
        when(mockMove.getMoveToColumn()).thenReturn(3);
        when(mockMove.getMoveToRow()).thenReturn(5);


        // When
        board.movePiece(mockMove);

        // Then
        verify(mockMove, times(2)).getMoveFromColumn();
        verify(mockMove, times(2)).getMoveFromRow();
        assertEquals(null, board.getPieceArray()[3][6]);
        
        verify(mockMove).getMoveToColumn();
        verify(mockMove).getMoveToRow();
        assertEquals(pieceBeingMoved, board.getPieceArray()[3][5]);

        verify(mockGui).updateBoardWithNewMove(mockMove, pieceBeingMoved);
    }

}