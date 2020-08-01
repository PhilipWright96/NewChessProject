package chess.board;

import chess.pieces.*;
import chess.util.Teams;

import java.util.ArrayList;

public class ChessBoard {
    private ChessBoardGUI chessBoardGUI = new ChessBoardGUI();
    private Piece[][] chessBoardPieces = new Piece[8][8];

    private ArrayList<Piece.Types> whitePieceOrder = new ArrayList<Piece.Types>() {{
        add(Piece.Types.ROOK);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.BISHOP);
        add(Piece.Types.QUEEN);
        add(Piece.Types.KING);
        add(Piece.Types.BISHOP);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.ROOK);
    }};

    private ArrayList<Piece.Types> blackPieceOrder = new ArrayList<Piece.Types>() {{
        add(Piece.Types.ROOK);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.BISHOP);
        add(Piece.Types.KING);
        add(Piece.Types.QUEEN);
        add(Piece.Types.BISHOP);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.ROOK);
    }};

    public ChessBoard() {
        addPawns();
        addSpecialPieces();
    }

    private void addPawns(){
        for (int k = 0; k < chessBoardPieces.length; k++){
            chessBoardPieces[k][1] = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD);
            chessBoardPieces[k][chessBoardPieces.length - 2] = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithPawns();
    }

    private void addSpecialPieces(){
        for (int l = 0; l < chessBoardPieces.length; l++){
            chessBoardPieces[l][1] = PieceFactory.constructPiece(blackPieceOrder.get(l), Teams.GOLD);
            chessBoardPieces[l][chessBoardPieces.length - 1] = PieceFactory.constructPiece(whitePieceOrder.get(l), Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithSpecialPieces();
    }
}