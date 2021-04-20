package test.java.com.philsfakecompany.chessapp.input.InputRetrieverTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.game.ICheckChecker.*;
import com.philsfakecompany.chessapp.input.InputChecker.*;
import com.philsfakecompany.chessapp.input.InputRetriever.*;
import com.philsfakecompany.chessapp.input.ScannerWrapper.*;
import com.philsfakecompany.chessapp.player.Player.*;
import org.junit.Test;

public class InputRetrieverTest {

    Player mockPlayer = mock(Player.class);
    IChessBoard mockBoard = mock(IChessBoard.class);
    InputChecker mockInputChecker = mock(InputChecker.class);
    ICheckChecker mockCheckChecker = mock(ICheckChecker.class);
    ScannerWrapper mockScannerWrapper = mock(ScannerWrapper.class);

    @Test
    public void getValidInputFromPlayer_withMoveWithCorrectSyntax_constructsANewMove() {
        // Given
        String dummyInput = "dummyInput";
        when(mockPlayer.getPlayerInput(any())).thenReturn(dummyInput);
        when(
            mockInputChecker.checkPlayerInput(
                dummyInput,
                mockPlayer,
                mockBoard,
                mockCheckChecker
            )
        )
            .thenReturn(true);

        // When
        InputRetriever retriever = new InputRetriever(
            mockInputChecker,
            mockScannerWrapper
        );
        ChessMove result = retriever.getValidInputFromPlayer(
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        verify(mockPlayer).getPlayerInput(mockScannerWrapper);
        verify(mockInputChecker)
            .checkPlayerInput(
                "dummyInput",
                mockPlayer,
                mockBoard,
                mockCheckChecker
            );
        assertNotNull(result);
    }

    @Test
    public void getValidInputFromPlayer_withMoveWithIncorrectSyntax_asksThePlayerForInputAgain() {
        // Given
        String dummyInput = "dummyInput";
        when(mockPlayer.getPlayerInput(any())).thenReturn(dummyInput);
        when(
            mockInputChecker.checkPlayerInput(
                dummyInput,
                mockPlayer,
                mockBoard,
                mockCheckChecker
            )
        )
            .thenReturn(false, true);

        // When
        InputRetriever retriever = new InputRetriever(
            mockInputChecker,
            mockScannerWrapper
        );
        retriever.getValidInputFromPlayer(
            mockPlayer,
            mockBoard,
            mockCheckChecker
        );

        // Then
        verify(mockPlayer, times(2)).getPlayerInput(any());
        verify(mockInputChecker, times(2))
            .checkPlayerInput(
                dummyInput,
                mockPlayer,
                mockBoard,
                mockCheckChecker
            );
    }
}
