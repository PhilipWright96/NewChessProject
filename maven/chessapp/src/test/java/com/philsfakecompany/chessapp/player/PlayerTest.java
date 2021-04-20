package test.java.com.philsfakecompany.chessapp.player.PlayerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.philsfakecompany.chessapp.input.ScannerWrapper.*;
import com.philsfakecompany.chessapp.player.Player.*;
import com.philsfakecompany.chessapp.util.Teams.*;
import org.junit.Test;

public class PlayerTest {

    ScannerWrapper mockScannerWrapper = mock(ScannerWrapper.class);
    Player player = new Player("Username", Teams.SILVER);

    @Test
    public void getPlayerInput_withTextEntered_returnsText() {
        // Given
        when(mockScannerWrapper.nextLine()).thenReturn("abc");

        // When
        String result = player.getPlayerInput(mockScannerWrapper);

        // Then
        assertEquals("getPlayerInput returning wrong text", "abc", result);
    }

    @Test
    public void getPlayerInput_withExceptionThrownWhenTextEntered_catchesExceptionAndPrintsStackTrace() {
        // Given
        IllegalStateException mockException = mock(IllegalStateException.class);
        when(mockScannerWrapper.nextLine()).thenThrow(mockException);

        // When
        String result = player.getPlayerInput(mockScannerWrapper);

        // Then
        verify(mockException).printStackTrace();
        assertEquals(
            "getPlayerInput not returning null on exception",
            null,
            result
        );
    }
}
