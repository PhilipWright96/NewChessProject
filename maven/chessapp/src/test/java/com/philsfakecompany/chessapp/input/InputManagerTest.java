package test.java.com.philsfakecompany.chessapp.input.InputManagerTest;

import static org.junit.Assert.*;

import com.philsfakecompany.chessapp.input.InputManager.*;
import com.philsfakecompany.chessapp.input.InputRetriever.*;
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
