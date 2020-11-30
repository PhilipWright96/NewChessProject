package chess.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameObserverTest {
    @Test
    public void update_setsHasFinishedToTrue(){
        // Given
        GameObserver observer = new GameObserver(new ChessGame());
        // When
        observer.update();
        // Then
        assertEquals("hasFinished property not set to true", true, observer.hasFinished);
    }

    @Test
    public void hasFinished_returnsCorrectValue(){
        // Given
        GameObserver observer = new GameObserver(new ChessGame());
        // Then
        assertEquals("hasFinished method not returning correct value", false, observer.hasFinished());
    }
}