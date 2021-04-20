package test.java.com.philsfakecompany.chessapp.input.InputCheckerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.game.ICheckChecker.*;
import com.philsfakecompany.chessapp.input.ClearPathChecker.*;
import com.philsfakecompany.chessapp.input.InputChecker.*;
import com.philsfakecompany.chessapp.pieces.IPiece.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;
import com.philsfakecompany.chessapp.player.Player.*;
import com.philsfakecompany.chessapp.util.Teams.*;
import org.junit.Test;

public class InputCheckerTest {

    ClearPathChecker mockPathChecker = mock(ClearPathChecker.class);
    Player mockPlayer = mock(Player.class);
    IChessBoard mockBoard = mock(IChessBoard.class);
    IPiece mockPiece = mock(IPiece.class);
    IPiece mockPieceBeingTaken = mock(IPiece.class);
    ICheckChecker mockCheckChecker = mock(ICheckChecker.class);

    String dummyInput = "d7-d5";

    @Test
    public void checkPlayerInput_withIncorrectInputSyntaxGiven_returnsFalse() {
        // Given

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            "badSyntax",
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withNoMatchingPieceFoundOnBoard_returnsFalse() {
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(null);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withGivenMoveNotActuallyMovingPiece_returnsFalse() {
        // Given
        dummyInput = "d7-d7";

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPlayerAttemptingToMoveOtherTeamsPiece_returnsFalse() {
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.GOLD);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        verify(mockPiece).getTeam();
        verify(mockPlayer).getTeam();
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPieceBeingMovedIncorrectly_returnsFalse() {
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(false);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        verify(mockPiece).moveValid(any(), eq(mockBoard));
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPieceTakingPieceOfSameTeam_returnsFalse() {
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any()))
            .thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.SILVER);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        verify(mockBoard).getPieceBeingTaken(any());
        verify(mockPieceBeingTaken).getTeam();
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withPathForAttemptedMoveNotClear_returnsFalse() {
        // Given
        IPiece[][] mockPieceBoard = new IPiece[1][1];

        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any()))
            .thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.GOLD);
        when(mockBoard.getPieceArray()).thenReturn(mockPieceBoard);
        when(mockPathChecker.pathForMoveClear(any(), eq(mockPieceBoard)))
            .thenReturn(false);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            null
        );

        // Then
        verify(mockPiece).getType();
        verify(mockBoard).getPieceArray();
        verify(mockPathChecker).pathForMoveClear(any(), eq(mockPieceBoard));
        assertEquals(false, result);
    }

    @Test
    public void checkPlayerInput_withKnightBeingMoved_returnsTrue() {
        // Given
        when(mockBoard.getPieceBeingMoved(any())).thenReturn(mockPiece);
        when(mockPiece.getTeam()).thenReturn(Teams.SILVER);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);
        when(mockPiece.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(mockBoard.getPieceBeingTaken(any()))
            .thenReturn(mockPieceBeingTaken);
        when(mockPieceBeingTaken.getTeam()).thenReturn(Teams.GOLD);
        when(mockPiece.getType()).thenReturn(Piece.Types.KNIGHT);
        when(
            mockCheckChecker.ownKingInCheck(
                any(ChessMove.class),
                eq(mockPlayer),
                eq(mockBoard),
                any(ClearPathChecker.class)
            )
        )
            .thenReturn(false);

        // When
        InputChecker checker = new InputChecker(mockPathChecker);
        boolean result = checker.checkPlayerInput(
            dummyInput,
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        verify(mockPiece).getType();
        assertEquals(true, result);
    }
}
