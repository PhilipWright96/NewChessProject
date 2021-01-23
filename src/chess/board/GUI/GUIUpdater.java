package chess.board.GUI;

import javax.swing.*;

import chess.game.ChessMove;
import chess.pieces.IPiece;

public class GUIUpdater {
    public void updateBoardWithPawns(JButton[][] chessBoardGUIButtons){
        for (int k = 0; k < chessBoardGUIButtons.length; k++){
            JButton goldSquare = chessBoardGUIButtons[k][1];
            ImageIcon goldIcon = new ImageIcon(ChessSprites.GOLD_PAWN);
            goldSquare.setIcon(goldIcon);

            JButton silverSquare = chessBoardGUIButtons[k][chessBoardGUIButtons.length - 2];
            ImageIcon silverIcon = new ImageIcon(ChessSprites.SILVER_PAWN);
            silverSquare.setIcon(silverIcon);
        }
    }

    public void updateBoardWithSpecialPieces(JButton[][] chessBoardGUIButtons){
        for (int l = 0, m = 0; l < chessBoardGUIButtons.length; l++, m++){
            JButton goldSquare = chessBoardGUIButtons[l][0];
            ImageIcon goldIcon = new ImageIcon(ChessSprites.SPRITES_IN_ORDER.get(l + m));
            goldSquare.setIcon(goldIcon);

            JButton silverSquare = chessBoardGUIButtons[l][chessBoardGUIButtons.length - 1];
            ImageIcon silverIcon = new ImageIcon(ChessSprites.SPRITES_IN_ORDER.get(l + m + 1));
            silverSquare.setIcon(silverIcon);
        }
    }

    public void updateBoardWithNewMove(JButton[][] chessBoardGUIButtons, ChessMove newMove, IPiece pieceBeingMoved){
        JButton moveFromSquare = chessBoardGUIButtons[newMove.getMoveFromColumn()][newMove.getMoveFromRow()];
        moveFromSquare.setIcon(null);

        ImageIcon pieceImage = new ImageIcon(ChessSprites.getCorrespondingImageFromPiece(pieceBeingMoved));
        JButton moveToSquare = chessBoardGUIButtons[newMove.getMoveToColumn()][newMove.getMoveToRow()];
        moveToSquare.setIcon(pieceImage);
    }
}
