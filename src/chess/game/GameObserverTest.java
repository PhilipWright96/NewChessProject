package chess.game;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class GameObserverTest {

  @Test
  public void update_setsHasFinishedToTrue() {
    // Given
    ChessGame mockGame = mock(ChessGame.class);
    GameObserver observer = new GameObserver(mockGame);
    // When
    observer.update();
    // Then
    assertEquals(
      "hasFinished property not set to true",
      true,
      observer.hasFinished
    );
  }
}
