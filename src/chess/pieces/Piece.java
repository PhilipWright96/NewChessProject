package chess.pieces;

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

     public abstract void move();
}