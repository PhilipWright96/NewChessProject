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
}
