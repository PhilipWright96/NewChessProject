package chess.board;

import chess.game.ChessMove;
import chess.pieces.IPiece;

public interface IChessBoard {
    public void initializeChessBoard();
    public IPiece[][] getPieceArray();
    public void movePiece(ChessMove inputMove);
    public IPiece getPieceBeingMoved(ChessMove move);
    public IPiece getPieceBeingTaken(ChessMove move);
    public ChessBoard.PieceToCoordinates getPieceToCoordinatesMap();
}
