package chess.board;

import chess.game.ChessMove;
import chess.pieces.*;
import chess.util.Teams;

import java.util.ArrayList;

public class ChessBoard implements IChessBoard {
    private IChessBoardGUI chessBoardGUI = new ChessBoardGUI();
    private IPiece[][] chessBoard = new Piece[8][8];

    private ArrayList<Piece.Types> pieceOrder = new ArrayList<Piece.Types>() {{
        add(Piece.Types.ROOK);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.BISHOP);
        add(Piece.Types.QUEEN);
        add(Piece.Types.KING);
        add(Piece.Types.BISHOP);
        add(Piece.Types.KNIGHT);
        add(Piece.Types.ROOK);
    }};

    public void initializeChessBoard() {
        this.chessBoardGUI.initializeBoardGUI();
        addPawns();
        addSpecialPieces();
    }

    public IPiece[][] getChessBoard(){
        return chessBoard;
    }

    public void movePiece(ChessMove inputMove){
        IPiece pieceBeingMoved = getPieceBeingMoved(inputMove);
        chessBoard[inputMove.getMoveFromColumn()][inputMove.getMoveFromRow()] = null;
        chessBoard[inputMove.getMoveToColumn()][inputMove.getMoveToRow()] = pieceBeingMoved;
        chessBoardGUI.updateBoardWithNewMove(inputMove, pieceBeingMoved);
    }

    public IPiece getPieceBeingMoved(ChessMove move){
        return chessBoard[move.getMoveFromColumn()][move.getMoveFromRow()];
    }

    public IPiece getPieceBeingTaken(ChessMove move){
        return chessBoard[move.getMoveToColumn()][move.getMoveToRow()];
    }

    public boolean pathForMoveClear(ChessMove move){
        if (move.isStraight()){
            if (move.isHorizontal()){
                if (pieceBlockingHorizontalMove(move)){
                    return false;
                }
            }
            else {
                if (pieceBlockingVerticalMove(move)){
                    return false;
                }
            }
        }
        else {
            if (pieceBlockingDiagonalMove(move)){
                return false;
            }
        }
        return true;
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
            chessBoard[l][0] = PieceFactory.constructPiece(pieceOrder.get(l), Teams.GOLD);
            chessBoard[l][chessBoard.length - 1] = PieceFactory.constructPiece(pieceOrder.get(l), Teams.SILVER);
        }

        chessBoardGUI.updateBoardWithSpecialPieces();
    }

    private boolean pieceBlockingHorizontalMove(ChessMove move){
        if (move.getMoveFromColumn() < move.getMoveToColumn()){
            System.out.println("Right");
            for (int i = move.getMoveFromColumn() + 1; i < move.getMoveToColumn(); i++){
                if (chessBoard[i][move.getMoveFromRow()] != null){
                    return true;
                }
            }
        }
        else {
            System.out.println("Left");
            for (int i = move.getMoveFromColumn() - 1; i > move.getMoveToColumn(); i--){
                if (chessBoard[i][move.getMoveFromRow()] != null){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pieceBlockingVerticalMove(ChessMove move){
        if (move.getMoveFromRow() < move.getMoveToRow()){
            System.out.println("Down");
            for (int i = move.getMoveFromRow() + 1; i < move.getMoveToRow(); i++){
                if (chessBoard[move.getMoveFromColumn()][i] != null){
                    return true;
                }
            }
        }
        else {
            System.out.println("Up");
            for (int i = move.getMoveFromRow() - 1; i > move.getMoveToRow(); i--){
                if (chessBoard[move.getMoveFromColumn()][i] != null){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pieceBlockingDiagonalMove(ChessMove move){
        int moveFromRow = move.getMoveFromRow();
        int moveFromCol = move.getMoveFromColumn();

        int moveToRow = move.getMoveToRow();
        int moveToCol = move.getMoveToColumn();

        System.out.println("Move from row is " + moveFromRow + " move from col is " + move.getMoveFromColumn());
        System.out.println("Move to row is " + moveToRow + " move to col is " + move.getMoveToColumn());

        // If moving up
        if (moveFromRow > moveToRow){
            // If moving right
            if (moveFromCol < moveToCol){
                for (int i = moveFromRow - 1, j = moveFromCol + 1; i > moveToRow; i--, j++){
                    if (chessBoard[j][i] != null){
                        return true;
                    }
                }
            }
            // Else moving left
            else {
                for (int row = moveFromRow - 1, col = moveFromCol - 1; row > moveToRow; row--, col--){
                    if (chessBoard[col][row] != null){
                        return true;
                    }
                }
            }
        }
        // Else moving down
        else {
            // if moving right 
            if (moveFromCol < moveToCol){
                for (int row = moveFromRow + 1, col = moveFromCol + 1; row < moveToRow; row++, col++){
                    if (chessBoard[col][row] != null){
                        return true;
                    }
                }
            }
            // Else moving left
            else {
                for (int row = moveFromRow + 1, col = moveFromCol - 1; row < moveToRow; row++, col--){
                    System.out.println("Checking row is " + row + " Checking col is " + col);
                    if (chessBoard[col][row] != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}