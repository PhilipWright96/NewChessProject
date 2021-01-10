package chess.board;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUISetter {
    
    public void arrangeGUIScreen(JPanel GUI, JPanel chessBoard){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.height * 2 / 3;
        GUI.setPreferredSize(new Dimension(width, height));

        GUI.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        GUI.add(tools, BorderLayout.PAGE_START);

        chessBoard.setBorder(new LineBorder(Color.BLACK));
        GUI.add(chessBoard);
    }

    public void arrangeButtons(JButton[][] chessBoardGUIButtons){
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < chessBoardGUIButtons.length; i++){
            for (int j = 0; j < chessBoardGUIButtons[i].length; j++){
                JButton button = new JButton();
                button.setMargin(buttonMargin);

                if (( j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                    button.setBackground(Color.WHITE);
                }
                else {
                    button.setBackground(Color.BLACK);
                }
                chessBoardGUIButtons[j][i] = button;
            }
        }
    }
}
