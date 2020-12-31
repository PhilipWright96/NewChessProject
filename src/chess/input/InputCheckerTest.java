package chess.input;

import org.junit.Test;

import chess.board.IChessBoard;
import chess.pieces.IPiece;
import chess.player.Player;
import chess.util.Teams;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InputCheckerTest {
    @Test
    public void checkPlayerInput_withIncorrectInputSyntaxGiven_returnsFalse(){
        // Given
        Player mockPlayer = mock(Player.class);
        IChessBoard mockBoard = mock(IChessBoard.class);    

        // When
        InputChecker checker = new InputChecker(new ClearPathChecker());
        boolean result = checker.checkPlayerInput("badSyntax", mockPlayer, mockBoard);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withNoMatchingPieceFoundOnBoard_returnsFalse(){
        // Given
        Player mockPlayer = mock(Player.class);
        IChessBoard mockBoard = mock(IChessBoard.class);
        String dummyInput = "d7-d6";

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(null);

        // When
        InputChecker checker = new InputChecker(new ClearPathChecker());
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withGivenMoveNotActuallyMovingPiece_returnsFalse(){
        // Given
        Player mockPlayer = mock(Player.class);
        IChessBoard mockBoard = mock(IChessBoard.class);
        String dummyInput = "d7-d7";
        IPiece mockPiece = mock(IPiece.class);

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);

        // When
        InputChecker checker = new InputChecker(new ClearPathChecker());
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPlayerAttemptingToMoveOtherTeamsPiece_returnsFalse(){
        // Given
        Player mockPlayer = mock(Player.class);
        IChessBoard mockBoard = mock(IChessBoard.class);
        String dummyInput = "d7-d5";
        IPiece mockPiece = mock(IPiece.class);

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.GOLD);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        // When
        InputChecker checker = new InputChecker(new ClearPathChecker());
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockPiece).getTeam();
        verify(mockPlayer).getTeam();
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPieceBeingMovedIncorrectly_returnsFalse(){
        // Given
        Player mockPlayer = mock(Player.class);
        IChessBoard mockBoard = mock(IChessBoard.class);
        String dummyInput = "d7-d5";
        IPiece mockPiece = mock(IPiece.class);

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(false);

        // When
        InputChecker checker = new InputChecker(new ClearPathChecker());
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockPiece).moveValid(any(), eq(mockBoard));
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPieceTakingPieceOfSameTeam_returnsFalse(){
        // Given
        Player mockPlayer = mock(Player.class);
        IChessBoard mockBoard = mock(IChessBoard.class);
        String dummyInput = "d7-d5";
        IPiece mockPiece = mock(IPiece.class);
        IPiece mockPieceBeingTaken = mock(IPiece.class);

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any())).thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.SILVER);

        // When
        InputChecker checker = new InputChecker(new ClearPathChecker());
        boolean result = checker.checkPlayerInput(dummyInput, mockPlayer, mockBoard);

        // Then
        verify(mockBoard).getPieceBeingTaken(any());
        verify(mockPieceBeingTaken).getTeam();
        assertEquals(false, result);
    }
}
