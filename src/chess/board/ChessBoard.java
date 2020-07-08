package chess.board;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import chess.util.ChessSprites;
import chess.pieces.*;

public class ChessBoard {
    private final JPanel GUI = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardGUI = new JButton[8][8];
    private Piece[][] chessBoardPieces = new Piece[8][8];

    private JPanel chessBoard;
    private static final String columns = "ABCDEFGH";

    public ChessBoard() {
        initializeBoard();
    }
    
    private void initializeBoard(){
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
        for (int i = 0; i < chessBoardGUI.length; i++){
            for (int j = 0; j < chessBoardGUI[i].length; j++){
                JButton button = new JButton();
                button.setMargin(buttonMargin);

                if (( j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                    button.setBackground(Color.WHITE);
                }
                else {
                    button.setBackground(Color.BLACK);
                }
                chessBoardGUI[j][i] = button;
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
                        chessBoard.add(chessBoardGUI[l][k]);
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

    public void addPiecesToBoard() {
       addPawns();
    }

    private void addPawns(){
        for (int k = 0; k < chessBoardGUI.length; k++){
            JButton square = chessBoardGUI[k][1];
            ImageIcon icon = new ImageIcon(ChessSprites.GOLD_PAWN);
            square.setIcon(icon);

            chessBoardPieces[k][1] = new Pawn(Piece.Teams.GOLD);
        }

        for (int l = 0; l < chessBoardGUI.length; l++){
            JButton square = chessBoardGUI[l][chessBoardGUI.length - 2];
            ImageIcon icon = new ImageIcon(ChessSprites.SILVER_PAWN);
            square.setIcon(icon);

            chessBoardPieces[l][chessBoardGUI.length - 2] = new Pawn(Piece.Teams.SILVER);
        }
    }

    public JComponent getChessBoard() {
        return chessBoard;
    }

    public JComponent getGUI(){
        return GUI;
    }
}