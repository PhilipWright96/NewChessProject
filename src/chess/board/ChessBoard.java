package chess.board;

import chess.game.ChessMove;
import chess.pieces.*;
import chess.util.Teams;
import chess.board.GUI.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessBoard implements IChessBoard {

    private IChessBoardGUI chessBoardGUI;
    private IPiece[][] pieceArray;
    private PieceToCoordinates allPiecesToCoordinates; 


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
        allPiecesToCoordinates = new PieceToCoordinates();
    }

    public void initializeChessBoard() {
        chessBoardGUI.initializeBoardGUI();
        addPawns();
        addSpecialPieces();
    }

    public IPiece[][] getPieceArray(){
        return pieceArray;
    }

    public void movePiece(ChessMove inputMove){
        IPiece pieceBeingMoved = getPieceBeingMoved(inputMove);
        pieceArray[inputMove.getMoveFromColumn()][inputMove.getMoveFromRow()] = null;
        pieceArray[inputMove.getMoveToColumn()][inputMove.getMoveToRow()] = pieceBeingMoved;
        
        Coordinates movedPieceCoordinates = pieceToCoordinates.get(pieceBeingMoved);
        movedPieceCoordinates.setRowAndColumnCoordinates(inputMove.getMoveToRow(), inputMove.getMoveToColumn());
        
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
            Piece goldPawn = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD);
            pieceArray[k][1] = goldPawn;
            allPiecesToCoordinates.storePieceWithCoordinates(goldPawn, k, 1);

            Piece silverPawn = PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER);
            pieceArray[k][pieceArray.length - 2] = silverPawn;
            allPiecesToCoordinates.storePieceWithCoordinates(silverPawn, k, pieceArray.length - 2);
        }

        chessBoardGUI.updateBoardWithPawns();
    }

    private void addSpecialPieces(){
        for (int l = 0; l < pieceArray.length; l++){
            Piece goldPiece = PieceFactory.constructPiece(pieceOrder.get(l), Teams.GOLD);
            pieceArray[l][0] = goldPiece;
            allPiecesToCoordinates.storePieceWithCoordinates(goldPiece, l, 0);

            Piece silverPiece = PieceFactory.constructPiece(pieceOrder.get(l), Teams.SILVER);
            pieceArray[l][pieceArray.length - 1] = silverPiece;
            allPiecesToCoordinates.storePieceWithCoordinates(silverPiece, l, pieceArray.length - 1);
        }

        chessBoardGUI.updateBoardWithSpecialPieces();
    }

    private class PieceToCoordinates {
        private HashMap<IPiece, Coordinates> silverPieceToCoordinates;
        private HashMap<IPiece, Coordinates> goldPieceToCoordinates;

        public PieceToCoordinates(){
            this.silverPieceToCoordinates = new HashMap<IPiece, Coordinates>();
            this.goldPieceToCoordinates = new HashMap<IPiece, Coordinates>();
        }

        public void storePieceWithCoordinates(IPiece piece, int rowCoordinate, int columnCoordinate){
            Coordinates coordinates = new Coordinates(rowCoordinate, columnCoordinate);
            if (piece.getTeam() == Teams.SILVER){
                silverPieceToCoordinates.put(piece, coordinates);
            }
            else {
                goldPieceToCoordinates.put(piece, coordinates);
            }
        }
    }

    private class Coordinates {
        private int rowCoordinate;
        private int columnCoordinate;

        public Coordinates(int rowCoordinate, int columnCoordinate){
            this.rowCoordinate = rowCoordinate;
            this.columnCoordinate = columnCoordinate;
        }

        public int getRowCoordinate(){
            return this.rowCoordinate;
        }

        public int getColumnCoordinate(){
            return this.columnCoordinate;
        }

        public void setRowAndColumnCoordinates(int rowCoordinate, int columnCoordinate){
            this.rowCoordinate = rowCoordinate;
            this.columnCoordinate = columnCoordinate;
        }
    }
}