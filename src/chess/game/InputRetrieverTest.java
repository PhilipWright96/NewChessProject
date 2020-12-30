package chess.game;

import org.junit.Test;

import chess.board.IChessBoard;
import chess.player.Player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InputRetrieverTest {

    Player mockPlayer = mock(Player.class);
    IChessBoard mockBoard = mock(IChessBoard.class);
    InputChecker mockInputChecker = mock(InputChecker.class);

    @Test
    public void getValidInputFromPlayer_withMoveWithCorrectSyntax_constructsANewMove(){

        // Given
        when(mockPlayer.getPlayerInput(any())).thenReturn("dummyInput");
        when(mockInputChecker.checkPlayerInput("dummyInput", mockPlayer, mockBoard)).thenReturn(true);

        // When
        InputRetriever retriever = new InputRetriever(mockInputChecker);
        ChessMove result = retriever.getValidInputFromPlayer(mockPlayer, mockBoard);

        // Then
        verify(mockPlayer).getPlayerInput(any());
        verify(mockInputChecker).checkPlayerInput("dummyInput", mockPlayer, mockBoard);
        assertNotNull(result);
    }

    @Test
    public void getValidInputFromPlayer_withMoveWithIncorrectSyntax_asksThePlayerForInputAgain(){

        // Given
        when(mockPlayer.getPlayerInput(any())).thenReturn("dummyInput");
        when(mockInputChecker.checkPlayerInput("dummyInput", mockPlayer, mockBoard)).thenReturn(false, true);

        // When
        InputRetriever retriever = new InputRetriever(mockInputChecker);
        retriever.getValidInputFromPlayer(mockPlayer, mockBoard);

        // Then
        verify(mockPlayer, times(2)).getPlayerInput(any());
        verify(mockInputChecker, times(2)).checkPlayerInput("dummyInput", mockPlayer, mockBoard);
    }
}
