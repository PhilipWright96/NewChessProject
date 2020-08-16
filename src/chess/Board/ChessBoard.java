package chess.board;

import chess.pieces.*;
import chess.util.Teams;

import java.util.ArrayList;

public class ChessBoard {
    private ChessBoardGUI chessBoardGUI = new ChessBoardGUI();
    private Piece[][] chessBoard = new Piece[8][8];

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

    public Piece[][] getChessBoard(){
        return chessBoard;
    }

    private void addPawns(){
        for (int k = 0; k < chessBoard.length; k++){
            chessBoard[k][1] = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD);
            chessBoard[k][chessBoard.length - 2] = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithPawns();
    }

    private void addSpecialPieces(){
        for (int l = 0; l < chessBoard.length; l++){
            chessBoard[l][0] = PieceFactory.constructPiece(blackPieceOrder.get(l), Teams.GOLD);
            chessBoard[l][chessBoard.length - 1] = PieceFactory.constructPiece(whitePieceOrder.get(l), Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithSpecialPieces();
    }
}