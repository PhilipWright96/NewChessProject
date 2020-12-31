package chess.game;

import org.junit.Test;

import chess.board.IChessBoard;
import chess.player.Player;
import chess.util.Teams;

import static org.mockito.Mockito.*;

public class ChessGameTest {
    @Test
    public void playGame_setsBoardAndPlaysThroughRounds(){

        // Given
        IChessBoard mockBoard = mock(IChessBoard.class);
        Player mockPlayerSilver = mock(Player.class);
        Player mockPlayerGold = mock(Player.class);
        InputRetriever mockInputRetriever = mock(InputRetriever.class);

        ChessMove mockSilverMove = mock(ChessMove.class);
        ChessMove mockGoldMove = mock(ChessMove.class);

        when(mockPlayerSilver.getTeam()).thenReturn(Teams.SILVER);
        when(mockInputRetriever.getValidInputFromPlayer(mockPlayerSilver, mockBoard)).thenReturn(mockSilverMove);
        when(mockInputRetriever.getValidInputFromPlayer(mockPlayerGold, mockBoard)).thenReturn(mockGoldMove);

        ChessGame game = new ChessGame(mockPlayerSilver, mockPlayerGold, mockBoard, mockInputRetriever);

        // When
        try {
            game.playGame();
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Then
        verify(mockBoard).initializeChessBoard();

        verify(mockInputRetriever, times(5)).getValidInputFromPlayer(mockPlayerSilver, mockBoard);
        verify(mockInputRetriever, times(5)).getValidInputFromPlayer(mockPlayerGold, mockBoard);

        verify(mockBoard, times(5)).movePiece(mockSilverMove);
        verify(mockBoard, times(5)).movePiece(mockGoldMove);

        verify(mockInputRetriever).closeScanner();
    }
}