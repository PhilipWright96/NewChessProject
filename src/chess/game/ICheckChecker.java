package chess.game;

import chess.board.IChessBoard;
import chess.player.Player;

public interface ICheckChecker {
    public boolean ownKingInCheck(ChessMove move, Player playerMoving, IChessBoard board);
    public boolean opposingKingInCheck(Player playerMoving, IChessBoard board);
}
