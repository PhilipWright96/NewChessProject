package chess.pieces;
import chess.pieces.Piece.*;

public class PieceFactory {
    public static Piece constructPiece (Piece.Types type, Piece.Teams team) {
        Piece chosenPiece; 
        switch (type) {
            case PAWN : 
                chosenPiece = (Piece) new Pawn();
                break;
            case ROOK : 
                chosenPiece = (Piece) new Rook(); 
                break;
            case KNIGHT : 
                chosenPiece = (Piece) new Knight();
                break;
            case BISHOP : 
                chosenPiece = (Piece) new Bishop();
                break;
            case QUEEN :
                chosenPiece = (Piece) new Queen();
                break;
            case KING : 
                chosenPiece = (Piece) new King();
                break;    
            default :
                chosenPiece = null;           
        }

        chosenPiece.setTeam(team);
        return chosenPiece;
    }
}