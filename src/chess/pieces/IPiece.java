package chess.pieces;

import chess.game.ChessMove;
import chess.pieces.Piece.Types;
import chess.util.Teams;

public interface IPiece {
    public int hashCode();
    public boolean equals(Object other);
    public void setTeam(Teams team);
    public Teams getTeam();
    public void setType(Types type);
    public Types getType();
    public abstract boolean moveValid(ChessMove move);
}
