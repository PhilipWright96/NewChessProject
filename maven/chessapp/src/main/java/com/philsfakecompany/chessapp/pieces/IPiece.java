package pieces;

import board.*;
import game.*;
import pieces.*;
import util.*;

public interface IPiece {
    public int hashCode();

    public boolean equals(Object other);

    public void setTeam(Teams team);

    public Teams getTeam();

    public void setType(Piece.Types type);

    public Piece.Types getType();

    public abstract boolean moveValid(ChessMove move, IChessBoard chessBoard);
}
