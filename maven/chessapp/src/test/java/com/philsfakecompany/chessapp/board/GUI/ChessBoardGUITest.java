package test.java.com.philsfakecompany.chessapp.board.GUI.ChessBoardGUI;

import static org.mockito.Mockito.*;

import com.philsfakecompany.chessapp.board.GUI.ChessBoardGUI.*;
import com.philsfakecompany.chessapp.board.GUI.GUISetter.*;
import com.philsfakecompany.chessapp.board.GUI.GUIUpdater.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.IPiece.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.Test;

public class ChessBoardGUITest {

    @Test
    public void initializeBoardGUI_callsCorrectSetterMethods() {
        // Given
        GUISetter mockGUISetter = mock(GUISetter.class);
        GUIUpdater mockGUIUpdater = mock(GUIUpdater.class);
        ChessBoardGUI boardGUI = new ChessBoardGUI(
            mockGUISetter,
            mockGUIUpdater
        );

        // When
        boardGUI.initializeBoardGUI();

        // Then
        verify(mockGUISetter)
            .arrangeGUIScreen(any(JPanel.class), any(JPanel.class));
        verify(mockGUISetter).arrangeButtons(any());
        verify(mockGUISetter).addButtonsToBoard(any(JPanel.class), any());
        verify(mockGUISetter)
            .addFrameToBoard(any(JPanel.class), any(JFrame.class));
    }

    @Test
    public void updateBoardWithPawns_callsCorrectUpdaterMethod() {
        // Given
        GUISetter mockGUISetter = mock(GUISetter.class);
        GUIUpdater mockGUIUpdater = mock(GUIUpdater.class);
        ChessBoardGUI boardGUI = new ChessBoardGUI(
            mockGUISetter,
            mockGUIUpdater
        );

        // When
        boardGUI.updateBoardWithPawns();

        // Then
        verify(mockGUIUpdater).updateBoardWithPawns(any());
    }

    @Test
    public void updateBoardWithSpecialPieces_callsCorrectUpdaterMethod() {
        // Given
        GUISetter mockGUISetter = mock(GUISetter.class);
        GUIUpdater mockGUIUpdater = mock(GUIUpdater.class);
        ChessBoardGUI boardGUI = new ChessBoardGUI(
            mockGUISetter,
            mockGUIUpdater
        );

        // When
        boardGUI.updateBoardWithSpecialPieces();

        // Then
        verify(mockGUIUpdater).updateBoardWithSpecialPieces(any());
    }

    @Test
    public void updateBoardWithNewMove_callsCorrectUpdaterMethod() {
        // Given
        GUISetter mockGUISetter = mock(GUISetter.class);
        GUIUpdater mockGUIUpdater = mock(GUIUpdater.class);
        ChessBoardGUI boardGUI = new ChessBoardGUI(
            mockGUISetter,
            mockGUIUpdater
        );

        ChessMove mockMove = mock(ChessMove.class);
        IPiece mockPiece = mock(IPiece.class);

        // When
        boardGUI.updateBoardWithNewMove(mockMove, mockPiece);

        // Then
        verify(mockGUIUpdater)
            .updateBoardWithNewMove(any(), eq(mockMove), eq(mockPiece));
    }
}
