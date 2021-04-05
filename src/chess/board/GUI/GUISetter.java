package chess.board.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUISetter {

    private static final String columns = "ABCDEFGH";

    public void arrangeGUIScreen(JPanel GUI, JPanel chessBoard) {
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

    public void arrangeButtons(JButton[][] chessBoardGUIButtons) {
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < chessBoardGUIButtons.length; i++) {
            for (int j = 0; j < chessBoardGUIButtons[i].length; j++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);

                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.BLACK);
                }
                chessBoardGUIButtons[j][i] = button;
            }
        }
    }

    public void addButtonsToBoard(
        JPanel chessBoard,
        JButton[][] chessBoardGUIButtons
    ) {
        chessBoard.add(new JLabel(""));
        for (int k = 0; k < 8; k++) {
            chessBoard.add(
                new JLabel(columns.substring(k, k + 1), SwingConstants.CENTER)
            );
        }
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                switch (l) {
                    case 0:
                        chessBoard.add(
                            new JLabel("" + (k + 1), SwingConstants.CENTER)
                        );
                    default:
                        chessBoard.add(chessBoardGUIButtons[l][k]);
                }
            }
        }
    }

    public void addFrameToBoard(JPanel GUI, JFrame frame) {
        frame.add(GUI);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);

        frame.pack();

        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
    }
}
