package chess;

abstract class Piece {
    enum Teams {
        WHITE, 
        BLACK
    }
    
    public abstract void move();
    public abstract char getType();
}