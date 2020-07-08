package chess.pieces;

public abstract class Piece {
    public enum Teams {
        SILVER, 
        GOLD
    }
    
    public abstract void move();
    public abstract Piece.Teams getType();
}