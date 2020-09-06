package chess.board;

import chess.game.ChessMove;
import chess.pieces.Piece;

public interface IChessBoard {
    public void initializeChessBoard();
    public Piece[][] getChessBoard();
    public void movePiece(ChessMove inputMove);
    public Piece getPieceBeingMovedFromBoard(ChessMove move);
}
