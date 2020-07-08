package chess.pieces;

public class Pawn extends Piece{
    private Piece.Teams team;

    public Pawn(Piece.Teams team){
        this.team = team;
    }
    public Piece.Teams getType(){
       if (this.team == Piece.Teams.SILVER){
           return Piece.Teams.SILVER;
       }
       else {
           return Piece.Teams.GOLD;
       }
    }
    public void move(){
    }
}