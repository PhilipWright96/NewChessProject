package chess.board;

import java.awt.*;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.swing.*;
import javax.swing.border.*;

public class GUISetterTest {

    @Test
    public void arrangeGUIScreen_setsGUIAndAddsItToChessBoard(){

        // Given
        JPanel mockGUI = mock((JPanel.class));
        JPanel mockChessBoard = mock((JPanel.class));

        GUISetter setter = new GUISetter();

        // When
        setter.arrangeGUIScreen(mockGUI, mockChessBoard);

        // Then
        verify(mockGUI).setPreferredSize(any(Dimension.class));
        verify(mockGUI).setBorder(any(EmptyBorder.class));
        verify(mockGUI).add(any(JToolBar.class), eq(BorderLayout.PAGE_START));
        verify(mockChessBoard).setBorder(any(LineBorder.class));

        verify(mockGUI).add(mockChessBoard);
    }

    @Test
    public void arrangeButtons_addsButtonsToArrayPassedIn(){

        // Given
        JButton[][] mockGUIButtons = new JButton[8][8];

        GUISetter setter = new GUISetter();

        // When
        setter.arrangeButtons(mockGUIButtons);

        // Then
        for (int i = 0; i < mockGUIButtons.length; i++){
            for (int j = 0; j < mockGUIButtons[i].length; j++){
                JButton button = mockGUIButtons[i][j];
                if (( j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                    assertEquals(Color.WHITE, button.getBackground());
                }
                else {
                    assertEquals(Color.BLACK, button.getBackground());
                }
            }
        }
    }

    @Test
    public void addFrameToBoard(){

        // Given
        JPanel mockGUI = mock(JPanel.class);
        JFrame mockFrame = mock(JFrame.class);

        GUISetter setter = new GUISetter();
        Dimension mockDimension = mock(Dimension.class);
        when(mockFrame.getSize()).thenReturn(mockDimension);

        // When
        setter.addFrameToBoard(mockGUI, mockFrame);

        // Then
        verify(mockFrame).add(mockGUI);
        verify(mockFrame).setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verify(mockFrame).setLocationByPlatform(true);

        verify(mockFrame).pack();
        verify(mockFrame).getSize();
        verify(mockFrame).setMinimumSize(mockDimension);
        verify(mockFrame).setVisible(true);
    }
    
}
