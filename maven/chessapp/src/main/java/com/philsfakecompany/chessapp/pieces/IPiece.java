package com.philsfakecompany.chessapp.pieces.IPiece;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;
import com.philsfakecompany.chessapp.util.Teams.*;

public interface IPiece {
    public int hashCode();

    public boolean equals(Object other);

    public void setTeam(Teams team);

    public Teams getTeam();

    public void setType(Piece.Types type);

    public Piece.Types getType();

    public abstract boolean moveValid(ChessMove move, IChessBoard chessBoard);
}
