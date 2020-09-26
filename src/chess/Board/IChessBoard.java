package chess.board;

import chess.game.ChessMove;
import chess.pieces.IPiece;

public interface IChessBoard {
    public void initializeChessBoard();
    public IPiece[][] getChessBoard();
    public void movePiece(ChessMove inputMove);
    public IPiece getPieceBeingMovedFromBoard(ChessMove move);
    public IPiece getPieceBeingMovedToBoard(ChessMove move);
}
