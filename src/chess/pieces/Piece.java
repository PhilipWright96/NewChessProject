package chess.pieces;

public abstract class Piece {
    public enum Teams {
        SILVER, 
        GOLD
    }

    public Piece.Teams team;
    
    public Piece.Teams getType(){
        if (this.team == Piece.Teams.SILVER){
            return Piece.Teams.SILVER;
        }
        else {
            return Piece.Teams.GOLD;
        }
     }

     public abstract void move();
}