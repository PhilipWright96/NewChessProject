package chess.game;

import static org.mockito.Mockito.*;

import chess.board.IChessBoard;
import chess.input.ClearPathChecker;
import chess.input.InputRetriever;
import chess.player.Player;
import chess.util.Teams;
import org.junit.Test;

public class ChessGameTest {

    @Test
    public void playGame_setsBoardAndPlaysThroughRounds() {
        // Given
        IChessBoard mockBoard = mock(IChessBoard.class);
        Player mockPlayerSilver = mock(Player.class);
        Player mockPlayerGold = mock(Player.class);
        InputRetriever mockInputRetriever = mock(InputRetriever.class);
        ICheckChecker mockCheckChecker = mock(ICheckChecker.class);

        ChessMove mockSilverMove = mock(ChessMove.class);
        ChessMove mockGoldMove = mock(ChessMove.class);

        when(mockPlayerSilver.getTeam()).thenReturn(Teams.SILVER);
        when(
            mockInputRetriever.getValidInputFromPlayer(
                mockPlayerSilver,
                mockBoard,
                mockCheckChecker
            )
        )
            .thenReturn(mockSilverMove);
        when(
            mockInputRetriever.getValidInputFromPlayer(
                mockPlayerGold,
                mockBoard,
                mockCheckChecker
            )
        )
            .thenReturn(mockGoldMove);

        ChessGame game = new ChessGame(
            mockPlayerSilver,
            mockPlayerGold,
            mockBoard,
            mockInputRetriever,
            mockCheckChecker
        );

        // When
        try {
            game.playGame();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Then
        verify(mockBoard).initializeChessBoard();

        verify(mockInputRetriever, times(5))
            .getValidInputFromPlayer(
                mockPlayerSilver,
                mockBoard,
                mockCheckChecker
            );
        verify(mockInputRetriever, times(5))
            .getValidInputFromPlayer(
                mockPlayerGold,
                mockBoard,
                mockCheckChecker
            );

        verify(mockBoard, times(5)).movePiece(mockSilverMove, true);
        verify(mockBoard, times(5)).movePiece(mockGoldMove, true);

        verify(mockCheckChecker, times(5))
            .opposingKingInCheck(
                eq(mockPlayerSilver),
                eq(mockBoard),
                any(ClearPathChecker.class)
            );
        verify(mockCheckChecker, times(5))
            .opposingKingInCheck(
                eq(mockPlayerGold),
                eq(mockBoard),
                any(ClearPathChecker.class)
            );

        verify(mockInputRetriever).closeScanner();
    }
}
