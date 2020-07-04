package chess.pieces;

public class Pawn extends Piece{
    private Piece.Teams team;

    public Pawn(Piece.Teams team){
        this.team = team;
    }
    public char getType(){
       if (this.team == Piece.Teams.BLACK){
           return 'p';
       }
       else {
           return 'P';
       }
    }
    public void move(){
    }
}