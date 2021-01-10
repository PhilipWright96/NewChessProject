package chess.board;

import java.awt.*;
import javax.swing.*;

import chess.game.ChessMove;
import chess.pieces.IPiece;

public class ChessBoardGUI implements IChessBoardGUI{
    private final JPanel GUI = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardGUIButtons = new JButton[8][8];
    private JPanel chessBoard = new JPanel(new GridLayout(0, 9));
    private GUISetter guiSetter;

    public ChessBoardGUI(GUISetter guiSetter){
        this.guiSetter = guiSetter;
    }

    public void initializeBoardGUI(){
        guiSetter.arrangeGUIScreen(GUI, chessBoard);

        guiSetter.arrangeButtons(chessBoardGUIButtons);

        guiSetter.addButtonsToBoard(chessBoard, chessBoardGUIButtons);

        guiSetter.addFrameToBoard(GUI);
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

    public void updateBoardWithNewMove(ChessMove newMove, IPiece pieceBeingMoved){
        System.out.println(" move from col is "  + newMove.getMoveFromColumn() + " move from row is " + newMove.getMoveFromRow());
        JButton moveFromSquare = chessBoardGUIButtons[newMove.getMoveFromColumn()][newMove.getMoveFromRow()];
        moveFromSquare.setIcon(null);

        ImageIcon pieceImage = new ImageIcon(ChessSprites.getCorrespondingImageFromPiece(pieceBeingMoved));
        JButton moveToSquare = chessBoardGUIButtons[newMove.getMoveToColumn()][newMove.getMoveToRow()];
        moveToSquare.setIcon(pieceImage);
    }
}