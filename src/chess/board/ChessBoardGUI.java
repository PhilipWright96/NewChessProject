package chess.board;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import chess.util.ChessSprites;

public class ChessBoardGUI {
    private final JPanel GUI = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardGUIButtons = new JButton[8][8];
    private JPanel chessBoard;

    private static final String columns = "ABCDEFGH";

    public ChessBoardGUI(){
        initializeBoardGUI();
    }

    public void updateBoardWithPawns(){
        for (int k = 0; k < chessBoardGUIButtons.length; k++){
            JButton goldSquare = chessBoardGUIButtons[k][1];
            ImageIcon goldIcon = new ImageIcon(ChessSprites.GOLD_PAWN);
            goldSquare.setIcon(goldIcon);

            JButton silverSquare = chessBoardGUIButtons[k][chessBoardGUIButtons.length - 2];
            ImageIcon silverIcon = new ImageIcon(ChessSprites.SILVER_PAWN);
            silverSquare.setIcon(silverIcon);
        }
    }

    public void updateBoardWithSpecialPieces(){
        for (int l = 0, m = 0; l < chessBoardGUIButtons.length; l++, m++){
            JButton goldSquare = chessBoardGUIButtons[l][0];
            ImageIcon goldIcon = new ImageIcon(ChessSprites.SPRITES_IN_ORDER.get(l + m));
            goldSquare.setIcon(goldIcon);

            JButton silverSquare = chessBoardGUIButtons[l][chessBoardGUIButtons.length - 1];
            ImageIcon silverIcon = new ImageIcon(ChessSprites.SPRITES_IN_ORDER.get(l + m + 1));
            silverSquare.setIcon(silverIcon);
        }
    }

    private void initializeBoardGUI(){
        arrangeGUI();

        createButtons();

        addButtonsToBoard();

        addFrameToBoard();
    }

    private void arrangeGUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.height * 2 / 3;
        GUI.setPreferredSize(new Dimension(width, height));

        GUI.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        GUI.add(tools, BorderLayout.PAGE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        GUI.add(chessBoard);
    }

    private void createButtons(){
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

    private void addButtonsToBoard(){
        chessBoard.add(new JLabel(""));
        for (int k = 0; k < 8; k++){
            chessBoard.add(new JLabel(columns.substring(k, k + 1), SwingConstants.CENTER));
        }
        for (int k = 0; k < 8; k++){
            for (int l = 0; l < 8; l++){
                switch (l) {
                    case 0: 
                        chessBoard.add(new JLabel("" + (k + 1), SwingConstants.CENTER));
                    default: 
                        chessBoard.add(chessBoardGUIButtons[l][k]);
                }
            }
        }
    }

    private void addFrameToBoard(){
        JFrame frame = new JFrame("Chess");
        frame.add(getGUI());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);

        frame.pack();

        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
    }

    private JComponent getGUI(){
        return GUI;
    }
}