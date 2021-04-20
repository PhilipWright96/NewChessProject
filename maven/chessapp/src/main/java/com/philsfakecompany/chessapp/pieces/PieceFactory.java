package com.philsfakecompany.chessapp.pieces.PieceFactory;

import com.philsfakecompany.chessapp.pieces.Bishop.*;
import com.philsfakecompany.chessapp.pieces.King.*;
import com.philsfakecompany.chessapp.pieces.Knight.*;
import com.philsfakecompany.chessapp.pieces.Pawn.*;
import com.philsfakecompany.chessapp.pieces.Piece.*;
import com.philsfakecompany.chessapp.pieces.Queen.*;
import com.philsfakecompany.chessapp.pieces.Rook.*;
import com.philsfakecompany.chessapp.util.Teams.*;

public class PieceFactory {

    public static Piece constructPiece(Piece.Types type, Teams team) {
        Piece chosenPiece;
        switch (type) {
            case PAWN:
                chosenPiece = (Piece) new Pawn();
                break;
            case ROOK:
                chosenPiece = (Piece) new Rook();
                break;
            case KNIGHT:
                chosenPiece = (Piece) new Knight();
                break;
            case BISHOP:
                chosenPiece = (Piece) new Bishop();
                break;
            case QUEEN:
                chosenPiece = (Piece) new Queen();
                break;
            case KING:
                chosenPiece = (Piece) new King();
                break;
            default:
                chosenPiece = null;
        }

        chosenPiece.setTeam(team);
        chosenPiece.setType(type);
        return chosenPiece;
    }
}
