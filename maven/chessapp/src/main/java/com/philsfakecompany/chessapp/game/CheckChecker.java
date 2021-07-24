package game;

import board.*;
import input.*;
import java.util.HashMap;
import java.util.Map;
import pieces.*;
import player.*;
import util.*;

public class CheckChecker implements ICheckChecker {

    public boolean ownKingInCheck(
        ChessMove move,
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    ) {
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        Coordinates ownKingCoordinates;
        HashMap<IPiece, Coordinates> otherPlayerPiecesToCoords;
        boolean ownKingInCheck;

        board.movePiece(move, false);

        if (playerMoving.getTeam() == Teams.SILVER) {
            ownKingCoordinates =
                pieceToCoordinatesMap.silverPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER)
                );
            otherPlayerPiecesToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
        } else {
            ownKingCoordinates =
                pieceToCoordinatesMap.goldPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD)
                );
            otherPlayerPiecesToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
        }

        ownKingInCheck =
            kingInCheck(
                otherPlayerPiecesToCoords,
                ownKingCoordinates,
                pathChecker,
                board
            );

        board.undoMovePiece(move);

        return ownKingInCheck;
    }

    public boolean opposingKingInCheck(
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    ) {
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        Coordinates opposingKingCoordinates;
        HashMap<IPiece, Coordinates> movingPlayerPiecesToCoords;

        if (playerMoving.getTeam() == Teams.SILVER) {
            opposingKingCoordinates =
                pieceToCoordinatesMap.goldPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
        } else {
            opposingKingCoordinates =
                pieceToCoordinatesMap.silverPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
        }

        return kingInCheck(
            movingPlayerPiecesToCoords,
            opposingKingCoordinates,
            pathChecker,
            board
        );
    }

    public boolean opposingKingInCheckmate(
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    ) {
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        Coordinates opposingKingCoordinates;
        HashMap<IPiece, Coordinates> movingPlayerPiecesToCoords;

        if (playerMoving.getTeam() == Teams.SILVER) {
            opposingKingCoordinates =
                pieceToCoordinatesMap.goldPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
        } else {
            opposingKingCoordinates =
                pieceToCoordinatesMap.silverPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
        }
        IPiece threatenedKing = board.getPieceArray()[opposingKingCoordinates.getColumnCoordinate()][opposingKingCoordinates.getRowCoordinate()];

        for (int row = -1; row < 2; row++) {
            for (int col = -1; col < 2; col++) {
                int newRow = opposingKingCoordinates.getRowCoordinate() + row;
                int newCol =
                    opposingKingCoordinates.getColumnCoordinate() + col;

                if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) {
                    System.out.println("Not valid - out of bounds!");
                    continue;
                }

                ChessMove potentialKingMove = new ChessMove(
                    opposingKingCoordinates.getColumnCoordinate(),
                    opposingKingCoordinates.getRowCoordinate(),
                    newCol,
                    newRow
                );

                if (
                    !moveValid(
                        threatenedKing,
                        potentialKingMove,
                        board,
                        pathChecker,
                        true
                    )
                ) {
                    System.out.println("Not valid - move invalid!");
                    continue;
                }

                board.movePiece(potentialKingMove, false);

                boolean kingCanEscape = true;

                for (Map.Entry<IPiece, Coordinates> entry : movingPlayerPiecesToCoords.entrySet()) {
                    IPiece piece = entry.getKey();
                    Coordinates coordinates = entry.getValue();

                    ChessMove potentialPieceMove = new ChessMove(
                        coordinates.getColumnCoordinate(),
                        coordinates.getRowCoordinate(),
                        newCol,
                        newRow
                    );

                    if (
                        moveValid(
                            piece,
                            potentialPieceMove,
                            board,
                            pathChecker,
                            false
                        )
                    ) {
                        kingCanEscape = false;
                        break;
                    }
                }

                board.undoMovePiece(potentialKingMove);

                if (kingCanEscape) {
                    System.out.println(
                        "Returning false because king can escape"
                    );
                    return false;
                }
            }
        }
        System.out.println("Returning true because king can't escape!");
        return true;
    }

    private boolean kingInCheck(
        HashMap<IPiece, Coordinates> playerPieceToCoords,
        Coordinates kingCoords,
        ClearPathChecker pathChecker,
        IChessBoard board
    ) {
        for (Map.Entry<IPiece, Coordinates> entry : playerPieceToCoords.entrySet()) {
            IPiece piece = entry.getKey();
            Coordinates coordinates = entry.getValue();

            ChessMove potentialMove = new ChessMove(
                coordinates.getColumnCoordinate(),
                coordinates.getRowCoordinate(),
                kingCoords.getColumnCoordinate(),
                kingCoords.getRowCoordinate()
            );

            if (moveValid(piece, potentialMove, board, pathChecker, false)) {
                System.out.println("Piece threatening is " + piece.getType());
                return true;
            }
        }

        return false;
    }

    private boolean moveValid(
        IPiece pieceMoving,
        ChessMove move,
        IChessBoard board,
        ClearPathChecker pathChecker,
        boolean checkMoveTargetSpace
    ) {
        boolean moveValid = pieceMoving.moveValid(move, board);
        boolean pathForPotentialMoveClear = false;

        if (moveValid) {
            pathForPotentialMoveClear =
                pathChecker.pathForMoveClear(
                    move,
                    board.getPieceArray(),
                    checkMoveTargetSpace
                );
        }
        return (moveValid && pathForPotentialMoveClear);
    }
}
