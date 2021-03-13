package chess.game;

import java.util.HashMap;
import java.util.Map;

import chess.board.ChessBoard;
import chess.board.IChessBoard;
import chess.board.ChessBoard.PieceToCoordinates;
import chess.input.ClearPathChecker;
import chess.pieces.IPiece;
import chess.pieces.PieceFactory;
import chess.pieces.Piece.Types;
import chess.player.Player;
import chess.util.Teams;

public class CheckChecker {
    public boolean ownKingInCheck(ChessMove move, Player playerMoving, IChessBoard board){
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        ChessBoard.Coordinates ownKingCoordinates;
        HashMap<IPiece, ChessBoard.Coordinates> otherPlayerPiecesToCoords;
        boolean ownKingInCheck;

        temporaryMovePiece(move, board);
        
        ClearPathChecker pathChecker = new ClearPathChecker();

        if (playerMoving.getTeam() == Teams.SILVER){
            ownKingCoordinates = pieceToCoordinatesMap.silverPieceToCoordinates.get(PieceFactory.constructPiece(Types.KING, Teams.SILVER));
            otherPlayerPiecesToCoords = pieceToCoordinatesMap.goldPieceToCoordinates;
        }
        else {
            ownKingCoordinates = pieceToCoordinatesMap.goldPieceToCoordinates.get(PieceFactory.constructPiece(Types.KING, Teams.GOLD));
            otherPlayerPiecesToCoords = pieceToCoordinatesMap.silverPieceToCoordinates;
        }

        ownKingInCheck = kingInCheck(otherPlayerPiecesToCoords, ownKingCoordinates, pathChecker, board);

        undoMovePiece(move, board);
        return ownKingInCheck;
    }

    public boolean opposingKingInCheck(Player playerMoving, IChessBoard board){
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        ChessBoard.Coordinates opposingKingCoordinates;
        HashMap<IPiece, ChessBoard.Coordinates> movingPlayerPiecesToCoords;

        ClearPathChecker pathChecker = new ClearPathChecker();

        if (playerMoving.getTeam() == Teams.SILVER){
            opposingKingCoordinates = pieceToCoordinatesMap.goldPieceToCoordinates.get(PieceFactory.constructPiece(Types.KING, Teams.GOLD));
            movingPlayerPiecesToCoords = pieceToCoordinatesMap.silverPieceToCoordinates;
        }
        else {
            opposingKingCoordinates = pieceToCoordinatesMap.silverPieceToCoordinates.get(PieceFactory.constructPiece(Types.KING, Teams.SILVER));
            movingPlayerPiecesToCoords = pieceToCoordinatesMap.goldPieceToCoordinates;
        }

        return kingInCheck(movingPlayerPiecesToCoords, opposingKingCoordinates, pathChecker, board);
    }

    private boolean kingInCheck(HashMap<IPiece, ChessBoard.Coordinates> playerPieceToCoords, ChessBoard.Coordinates kingCoords, ClearPathChecker pathChecker, IChessBoard board){
        for (Map.Entry<IPiece, ChessBoard.Coordinates> entry: playerPieceToCoords.entrySet()){
            IPiece piece = entry.getKey();
            ChessBoard.Coordinates coordinates = entry.getValue();
            boolean potentialMoveValid = false;
            boolean pathForPotentialMoveClear = false;

            ChessMove potentialMove = new ChessMove(coordinates.getColumnCoordinate(), coordinates.getRowCoordinate(), kingCoords.getColumnCoordinate(), kingCoords.getRowCoordinate());

            potentialMoveValid = piece.moveValid(potentialMove, board);

            if (potentialMoveValid){
                if (piece.getType() == Types.KNIGHT){
                    pathForPotentialMoveClear = true;
                }
                else {
                    pathForPotentialMoveClear = pathChecker.pathForMoveClear(potentialMove, board.getPieceArray());
                }
            }
            
            if (potentialMoveValid && pathForPotentialMoveClear){
                System.out.println("Kings col is " + kingCoords.getColumnCoordinate() + " and row is " + kingCoords.getRowCoordinate());
                System.out.println("Piece threatening is " + piece.getType());
                return true;
            }
        }

        return false;
    }

    private void temporaryMovePiece(ChessMove move, IChessBoard board){
        IPiece[][] pieceArray = board.getPieceArray();
        IPiece pieceBeingMoved = pieceArray[move.getMoveFromColumn()][move.getMoveFromRow()];;
        pieceArray[move.getMoveFromColumn()][move.getMoveFromRow()] = null;
        pieceArray[move.getMoveToColumn()][move.getMoveToRow()] = pieceBeingMoved;
        
        PieceToCoordinates allPiecesToCoordinates = board.getPieceToCoordinatesMap();
        allPiecesToCoordinates.updatePieceWithNewCoordinates(pieceBeingMoved, move.getMoveToRow(), move.getMoveToColumn());
    }

    private void undoMovePiece(ChessMove move, IChessBoard board){
        IPiece[][] pieceArray = board.getPieceArray();
        IPiece pieceAlreadyMoved = pieceArray[move.getMoveToColumn()][move.getMoveToRow()];;
        pieceArray[move.getMoveToColumn()][move.getMoveToRow()] = null;
        pieceArray[move.getMoveFromColumn()][move.getMoveFromRow()] = pieceAlreadyMoved;
        
        PieceToCoordinates allPiecesToCoordinates = board.getPieceToCoordinatesMap();
        allPiecesToCoordinates.updatePieceWithNewCoordinates(pieceAlreadyMoved, move.getMoveFromRow(), move.getMoveFromColumn());
    }
}
