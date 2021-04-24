package board;

import game.*;
import pieces.*;

public interface IChessBoard {
    public void initializeChessBoard();

    public IPiece[][] getPieceArray();

    public void movePiece(ChessMove inputMove, boolean updateGUI);

    public void undoMovePiece(ChessMove move);

    public IPiece getPieceBeingMoved(ChessMove move);

    public IPiece getPieceBeingTaken(ChessMove move);

    public ChessBoard.PieceToCoordinates getPieceToCoordinatesMap();
}
