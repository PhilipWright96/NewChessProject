package chess.game;

import java.util.HashMap;
import java.util.Map;

import chess.board.ChessBoard;
import chess.board.IChessBoard;
import chess.input.ClearPathChecker;
import chess.pieces.PieceFactory;
import chess.pieces.Piece.Types;
import chess.player.Player;
import chess.util.Teams;
import chess.pieces.IPiece;

public class GameObserver {
    IChessGame game;
    boolean hasFinished = false;
    public GameObserver(IChessGame game){
        this.game = game;
        this.game.attach(this);
    }

    public void update(){
        hasFinished = true;
    }

    public boolean hasFinished() {
        return hasFinished;
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

        for (Map.Entry<IPiece, ChessBoard.Coordinates> entry: movingPlayerPiecesToCoords.entrySet()){
            IPiece piece = entry.getKey();
            ChessBoard.Coordinates coordinates = entry.getValue();
            boolean potentialMoveValid = false;
            boolean pathForPotentialMoveClear = false;

            ChessMove potentialMove = new ChessMove(coordinates.getColumnCoordinate(), coordinates.getRowCoordinate(), opposingKingCoordinates.getColumnCoordinate(), opposingKingCoordinates.getRowCoordinate());

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
                return true;
            }
        }

        return false;
    }
}