package chess.pieces;

public abstract class Piece {
    public enum Teams {
        SILVER, 
        GOLD
    }

    public enum Types {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    public Piece.Teams team;

    public void setTeam(Piece.Teams team){
        this.team = team;
    }
    
    public Piece.Teams getTeam(){
        if (this.team == Piece.Teams.SILVER){
            return Piece.Teams.SILVER;
        }
        else {
            return Piece.Teams.GOLD;
        }
     }

     public abstract void move();
}