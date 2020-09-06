package chess.pieces;

import java.util.Objects;

import chess.util.Teams;

public abstract class Piece {

    public enum Types {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    public Teams team;
    public Types type;

    public int hashCode() {
        return Objects.hash(this.team, this.type);
    }

    public boolean equals(Object other){
        if (other == this){
            return true;
        }

        if (other instanceof Piece){
            Piece otherPiece = (Piece) other;
            return otherPiece.team == this.team && otherPiece.type == this.type;
        }

        return false;
    }

    public void setTeam(Teams team){
        this.team = team;
    }
    
    public Teams getTeam(){
        if (this.team == Teams.SILVER){
            return Teams.SILVER;
        }
        else {
            return Teams.GOLD;
        }
     }

     public void setType(Types type){
        this.type = type;
     }

     public Types getType(){
         return this.type;
     }

     public abstract void move();
}