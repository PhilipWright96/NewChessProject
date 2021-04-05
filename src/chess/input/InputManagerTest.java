package chess.input;

import static org.junit.Assert.*;

import org.junit.Test;

public class InputManagerTest {

    @Test
    public void buildRetriever_returnsARetriever() {
        // Given
        InputManager inputManager = new InputManager();

        // When
        InputRetriever result = inputManager.buildRetriever();

        // Then
        assertEquals(true, result != null);
    }
}
