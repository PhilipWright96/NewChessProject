package com.philsfakecompany.chessapp.board.IChessBoard;

import com.philsfakecompany.chessapp.board.ChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.IPiece.*;

public interface IChessBoard {
    public void initializeChessBoard();

    public IPiece[][] getPieceArray();

    public void movePiece(ChessMove inputMove, boolean updateGUI);

    public void undoMovePiece(ChessMove move);

    public IPiece getPieceBeingMoved(ChessMove move);

    public IPiece getPieceBeingTaken(ChessMove move);

    public ChessBoard.PieceToCoordinates getPieceToCoordinatesMap();
}
