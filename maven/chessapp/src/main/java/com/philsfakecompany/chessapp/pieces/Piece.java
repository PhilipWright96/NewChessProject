package com.philsfakecompany.chessapp.pieces.Piece;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.pieces.IPiece.*;
import com.philsfakecompany.chessapp.util.Teams.*;
import java.util.Objects;

public abstract class Piece implements IPiece {

    public enum Types {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING,
    }

    private Teams team;
    private Types type;

    public int hashCode() {
        return Objects.hash(this.team, this.type);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Piece) {
            Piece otherPiece = (Piece) other;
            return (
                otherPiece.team == this.team && otherPiece.type == this.type
            );
        }

        return false;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public Teams getTeam() {
        if (this.team == Teams.SILVER) {
            return Teams.SILVER;
        } else {
            return Teams.GOLD;
        }
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return this.type;
    }

    protected boolean isMoving(ChessMove move) {
        if (move.getRowChangeNum() == 0 && move.getColumnChangeNum() == 0) {
            return false;
        }
        return true;
    }

    public abstract boolean moveValid(ChessMove move, IChessBoard chessBoard);
}
