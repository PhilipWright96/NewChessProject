package chess.board;

import chess.pieces.*;

public class ChessBoard {
    private ChessBoardGUI chessBoardGUI = new ChessBoardGUI();
    private Piece[][] chessBoardPieces = new Piece[8][8];

    public ChessBoard() {
    }

    public void addPiecesToBoard() {
       addPawns();
    }

    private void addPawns(){
        for (int k = 0; k < chessBoardPieces.length; k++){
            chessBoardPieces[k][1] = new Pawn(Piece.Teams.GOLD);
            chessBoardPieces[k][chessBoardPieces.length - 2] = new Pawn(Piece.Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithPawns();
    }
}