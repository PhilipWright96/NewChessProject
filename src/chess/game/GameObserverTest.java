package chess.game;

import org.junit.Test;

import chess.player.Player;

import static org.junit.Assert.*;

public class GameObserverTest {
    @Test
    public void update_setsHasFinishedToTrue(){
        // Given
        ChessGame game = new ChessGame(new Player("1", null), new Player("2", null));
        GameObserver observer = new GameObserver(game);
        // When
        observer.update();
        // Then
        assertEquals("hasFinished property not set to true", true, observer.hasFinished);
    }

    @Test
    public void hasFinished_returnsCorrectValue(){
        // Given
        ChessGame game = new ChessGame(new Player("1", null), new Player("2", null));
        GameObserver observer = new GameObserver(game);

        // Then
        assertEquals("hasFinished method not returning correct value", false, observer.hasFinished());
    }
}