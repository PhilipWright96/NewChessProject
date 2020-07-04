package chess.pieces;

public abstract class Piece {
    public enum Teams {
        WHITE, 
        BLACK
    }
    
    public abstract void move();
    public abstract char getType();
}