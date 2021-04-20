package com.philsfakecompany.chessapp.board.GUI.IChessBoardGUI;

import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.IPiece.*;

public interface IChessBoardGUI {
    public void initializeBoardGUI();

    public void updateBoardWithPawns();

    public void updateBoardWithSpecialPieces();

    public void updateBoardWithNewMove(
        ChessMove newMove,
        IPiece pieceBeingMoved
    );
}
