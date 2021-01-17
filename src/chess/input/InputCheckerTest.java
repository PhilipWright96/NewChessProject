package chess.input;

import org.junit.Test;

import chess.board.IChessBoard;
import chess.pieces.IPiece;
import chess.pieces.Piece.Types;
import chess.player.Player;
import chess.util.Teams;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InputCheckerTest {

    ClearPathChecker mockPathChecker = mock(ClearPathChecker.class); 
    Player mockPlayer = mock(Player.class);
    IChessBoard mockBoard = mock(IChessBoard.class);
    IPiece mockPiece = mock(IPiece.class);
    IPiece mockPieceBeingTaken = mock(IPiece.class);
  
    String dummyInput = "d7-d5"; 

    @Test
    public void checkPlayerInput_withIncorrectInputSyntaxGiven_returnsFalse(){
        // Given

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput("badSyntax", mockPlayer, mockBoard);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withNoMatchingPieceFoundOnBoard_returnsFalse(){
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(null);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withGivenMoveNotActuallyMovingPiece_returnsFalse(){
        // Given
        dummyInput = "d7-d7";

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPlayerAttemptingToMoveOtherTeamsPiece_returnsFalse(){
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.GOLD);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockPiece).getTeam();
        verify(mockPlayer).getTeam();
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPieceBeingMovedIncorrectly_returnsFalse(){
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(false);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockPiece).moveValid(any(), eq(mockBoard));
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPieceTakingPieceOfSameTeam_returnsFalse(){
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any())).thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.SILVER);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockBoard).getPieceBeingTaken(any());
        verify(mockPieceBeingTaken).getTeam();
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPathForAttemptedMoveNotClear_returnsFalse(){
        // Given
        IPiece[][] mockPieceBoard = new IPiece[1][1];

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any())).thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.GOLD);
        when(mockBoard.getChessBoard()).thenReturn(mockPieceBoard);
        when(mockPathChecker.pathForMoveClear(any(), eq(mockPieceBoard))).thenReturn(false);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockPiece).getType();
        verify(mockBoard).getChessBoard();
        verify(mockPathChecker).pathForMoveClear(any(), eq(mockPieceBoard));
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withKnightBeingMoved_returnsTrue(){
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any())).thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.GOLD);
        when(mockPiece.getType()).thenReturn(Types.KNIGHT);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockPiece).getType();
        assertEquals(true, result);
    }
}
