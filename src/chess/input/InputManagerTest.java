package chess.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputManagerTest {

    @Test
    public void buildRetriever_returnsARetriever(){

        // Given
        InputManager inputManager = new InputManager();

        // When
        InputRetriever result = inputManager.buildRetriever();

        // Then
        assertEquals(true, result != null);
    }
    
}
