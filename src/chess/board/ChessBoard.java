package chess.board;

import chess.game.ChessMove;
import chess.pieces.*;
import chess.util.Teams;
import chess.board.GUI.*;

import java.util.ArrayList;

public class ChessBoard implements IChessBoard {

    private IChessBoardGUI chessBoardGUI;
    private IPiece[][] pieceArray;

    public static ArrayList<Piece.Types> pieceOrder = new ArrayList<Piece.Types>() {{
        add(Piece.Types.ROOK);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.BISHOP);
        add(Piece.Types.QUEEN);
        add(Piece.Types.KING);
        add(Piece.Types.BISHOP);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.ROOK);
    }};

    public ChessBoard(IChessBoardGUI boardGUI){
        chessBoardGUI = boardGUI;
        pieceArray = new Piece[8][8];
    }

    public void initializeChessBoard() {
        chessBoardGUI.initializeBoardGUI();
        addPawns();
        addSpecialPieces();
    }

    public IPiece[][] getChessBoard(){
        return pieceArray;
    }

    public void movePiece(ChessMove inputMove){
        IPiece pieceBeingMoved = getPieceBeingMoved(inputMove);
        pieceArray[inputMove.getMoveFromColumn()][inputMove.getMoveFromRow()] = null;
        pieceArray[inputMove.getMoveToColumn()][inputMove.getMoveToRow()] = pieceBeingMoved;
        chessBoardGUI.updateBoardWithNewMove(inputMove, pieceBeingMoved);
    }

    public IPiece getPieceBeingMoved(ChessMove move){
        return pieceArray[move.getMoveFromColumn()][move.getMoveFromRow()];
    }

    public IPiece getPieceBeingTaken(ChessMove move){
        return pieceArray[move.getMoveToColumn()][move.getMoveToRow()];
    }

    private void addPawns(){
        for (int k = 0; k < pieceArray.length; k++){
            pieceArray[k][1] = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD);
            pieceArray[k][pieceArray.length - 2] = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithPawns();
    }

    private void addSpecialPieces(){
        for (int l = 0; l < pieceArray.length; l++){
            pieceArray[l][0] = PieceFactory.constructPiece(pieceOrder.get(l), Teams.GOLD);
            pieceArray[l][pieceArray.length - 1] = PieceFactory.constructPiece(pieceOrder.get(l), Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithSpecialPieces();
    }
    
}