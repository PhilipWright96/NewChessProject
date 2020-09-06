package chess.board;

import chess.game.ChessMove;
import chess.pieces.Piece;

public interface IChessBoardGUI {
    public void initializeBoardGUI();
    public void updateBoardWithPawns();
    public void updateBoardWithSpecialPieces();
    public void updateBoardWithNewMove(ChessMove newMove, Piece pieceBeingMoved);
}
