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
        ChessBoard.Coordinates ownKingCoordinates;
        HashMap<IPiece, ChessBoard.Coordinates> otherPlayerPiecesToCoords;
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
        ChessBoard.Coordinates opposingKingCoordinates;
        HashMap<IPiece, ChessBoard.Coordinates> movingPlayerPiecesToCoords;

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

    private boolean kingInCheck(
        HashMap<IPiece, ChessBoard.Coordinates> playerPieceToCoords,
        ChessBoard.Coordinates kingCoords,
        ClearPathChecker pathChecker,
        IChessBoard board
    ) {
        for (Map.Entry<IPiece, ChessBoard.Coordinates> entry : playerPieceToCoords.entrySet()) {
            IPiece piece = entry.getKey();
            ChessBoard.Coordinates coordinates = entry.getValue();

            boolean potentialMoveValid = false;
            boolean pathForPotentialMoveClear = false;

            ChessMove potentialMove = new ChessMove(
                coordinates.getColumnCoordinate(),
                coordinates.getRowCoordinate(),
                kingCoords.getColumnCoordinate(),
                kingCoords.getRowCoordinate()
            );

            potentialMoveValid = piece.moveValid(potentialMove, board);

            if (potentialMoveValid) {
                if (piece.getType() == Piece.Types.KNIGHT) {
                    pathForPotentialMoveClear = true;
                } else {
                    pathForPotentialMoveClear =
                        pathChecker.pathForMoveClear(
                            potentialMove,
                            board.getPieceArray()
                        );
                }
            }

            if (potentialMoveValid && pathForPotentialMoveClear) {
                System.out.println("Piece threatening is " + piece.getType());
                return true;
            }
        }

        return false;
    }

    public boolean opposingKingInCheckmate(
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    ) {
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        ChessBoard.Coordinates opposingKingCoordinates;
        HashMap<IPiece, ChessBoard.Coordinates> movingPlayerPiecesToCoords;

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
                    continue;
                }

                ChessMove potentialKingMove = new ChessMove(
                    opposingKingCoordinates.getColumnCoordinate(),
                    opposingKingCoordinates.getRowCoordinate(),
                    newCol,
                    newRow
                );

                boolean kingMoveValid = threatenedKing.moveValid(
                    potentialKingMove,
                    board
                );
                boolean pathForPotentialKingMoveClear = false;

                if (kingMoveValid) {
                    pathForPotentialKingMoveClear =
                        pathChecker.pathForMoveClear(
                            potentialKingMove,
                            board.getPieceArray()
                        );
                }
                if (!kingMoveValid || !pathForPotentialKingMoveClear) {
                    System.out.println("King move not valid!");
                    continue;
                }

                board.movePiece(potentialKingMove, false);

                boolean kingCanEscape = true;

                for (Map.Entry<IPiece, ChessBoard.Coordinates> entry : movingPlayerPiecesToCoords.entrySet()) {
                    IPiece piece = entry.getKey();
                    boolean potentialMoveValid = false;
                    boolean pathForPotentialMoveClear = false;
                    ChessBoard.Coordinates coordinates = entry.getValue();

                    ChessMove potentialPieceMove = new ChessMove(
                        coordinates.getColumnCoordinate(),
                        coordinates.getRowCoordinate(),
                        newCol,
                        newRow
                    );

                    potentialMoveValid =
                        piece.moveValid(potentialPieceMove, board);

                    if (potentialMoveValid) {
                        if (piece.getType() == Piece.Types.KNIGHT) {
                            pathForPotentialMoveClear = true;
                        } else {
                            pathForPotentialMoveClear =
                                pathChecker.pathForMoveClear(
                                    potentialPieceMove,
                                    board.getPieceArray()
                                );
                        }
                    }
                    if (potentialMoveValid && pathForPotentialMoveClear) {
                        System.out.println(
                            "Marking space as not safe! Breaking - no point checking other pieces"
                        );
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
}
