package com.philsfakecompany.chessapp.board.GUI.ChessBoardGUI;

import com.philsfakecompany.chessapp.board.GUI.GUISetter.*;
import com.philsfakecompany.chessapp.board.GUI.GUIUpdater.*;
import com.philsfakecompany.chessapp.board.GUI.IChessBoardGUI.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.IPiece.*;
import java.awt.*;
import javax.swing.*;

public class ChessBoardGUI implements IChessBoardGUI {

    private final JPanel GUI = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardGUIButtons = new JButton[8][8];
    private JPanel chessBoard = new JPanel(new GridLayout(0, 9));
    private GUISetter guiSetter;
    private GUIUpdater guiUpdater;

    public ChessBoardGUI(GUISetter guiSetter, GUIUpdater guiUpdater) {
        this.guiSetter = guiSetter;
        this.guiUpdater = guiUpdater;
    }

    public void initializeBoardGUI() {
        guiSetter.arrangeGUIScreen(GUI, chessBoard);

        guiSetter.arrangeButtons(chessBoardGUIButtons);

        guiSetter.addButtonsToBoard(chessBoard, chessBoardGUIButtons);

        JFrame frame = new JFrame("Chess");
        guiSetter.addFrameToBoard(GUI, frame);
    }

    public void updateBoardWithPawns() {
        guiUpdater.updateBoardWithPawns(chessBoardGUIButtons);
    }

    public void updateBoardWithSpecialPieces() {
        guiUpdater.updateBoardWithSpecialPieces(chessBoardGUIButtons);
    }

    public void updateBoardWithNewMove(
        ChessMove newMove,
        IPiece pieceBeingMoved
    ) {
        guiUpdater.updateBoardWithNewMove(
            chessBoardGUIButtons,
            newMove,
            pieceBeingMoved
        );
    }
}
