package game;

import board.*;
import input.*;
import java.util.ArrayList;
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
        ArrayList<Coordinates> otherPlayerPieceCoords;
        boolean ownKingInCheck;

        board.movePiece(move, false);

        if (playerMoving.getTeam() == Teams.SILVER) {
            ownKingCoordinates =
                pieceToCoordinatesMap.silverPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER)
                );
            otherPlayerPiecesToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
            otherPlayerPieceCoords = pieceToCoordinatesMap.goldPieceCoordinates;
        } else {
            ownKingCoordinates =
                pieceToCoordinatesMap.goldPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD)
                );
            otherPlayerPiecesToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
            otherPlayerPieceCoords =
                pieceToCoordinatesMap.silverPieceCoordinates;
        }

        ownKingInCheck =
            kingInCheck(
                otherPlayerPieceCoords,
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
        ArrayList<Coordinates> movingPlayerPieceCoords;

        if (playerMoving.getTeam() == Teams.SILVER) {
            opposingKingCoordinates =
                pieceToCoordinatesMap.goldPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
            movingPlayerPieceCoords =
                pieceToCoordinatesMap.silverPieceCoordinates;
        } else {
            opposingKingCoordinates =
                pieceToCoordinatesMap.silverPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
            movingPlayerPieceCoords =
                pieceToCoordinatesMap.goldPieceCoordinates;
        }

        return kingInCheck(
            movingPlayerPieceCoords,
            opposingKingCoordinates,
            pathChecker,
            board
        );
    }

    public boolean opposingKingInCheckmate(
        Player playerMoving,
        IChessBoard board,
        ChessMove checkMove,
        ClearPathChecker pathChecker
    ) {
        ChessBoard.PieceToCoordinates pieceToCoordinatesMap = board.getPieceToCoordinatesMap();
        Coordinates opposingKingCoordinates;
        HashMap<IPiece, Coordinates> movingPlayerPiecesToCoords;
        ArrayList<Coordinates> movingPlayerPieceCoords;
        HashMap<IPiece, Coordinates> nonMovingPlayerPieceToCoords;
        ArrayList<Coordinates> nonMovingPlayerPieceCoords;

        if (playerMoving.getTeam() == Teams.SILVER) {
            opposingKingCoordinates =
                pieceToCoordinatesMap.goldPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
            movingPlayerPieceCoords =
                pieceToCoordinatesMap.silverPieceCoordinates;

            nonMovingPlayerPieceToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
            nonMovingPlayerPieceCoords =
                pieceToCoordinatesMap.goldPieceCoordinates;
        } else {
            opposingKingCoordinates =
                pieceToCoordinatesMap.silverPieceToCoordinates.get(
                    PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER)
                );
            movingPlayerPiecesToCoords =
                pieceToCoordinatesMap.goldPieceToCoordinates;
            movingPlayerPieceCoords =
                pieceToCoordinatesMap.goldPieceCoordinates;

            nonMovingPlayerPieceToCoords =
                pieceToCoordinatesMap.silverPieceToCoordinates;
            nonMovingPlayerPieceCoords =
                pieceToCoordinatesMap.silverPieceCoordinates;
        }
        IPiece threatenedKing = board.getPieceArray()[opposingKingCoordinates.getColumnCoordinate()][opposingKingCoordinates.getRowCoordinate()];

        // King moving through potential moves
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

                for (Coordinates coordinates : movingPlayerPieceCoords) {
                    IPiece piece = board.getPieceArray()[coordinates.getColumnCoordinate()][coordinates.getRowCoordinate()];

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

        // Add allied block or interrupt block check in here?
        if (
            threateningPieceCanBeTaken(
                opposingKingCoordinates,
                nonMovingPlayerPieceCoords,
                checkMove,
                board,
                pathChecker
            )
        ) {
            return false;
        }
        if (
            moveCanBeBlockedByAlliedPiece(
                nonMovingPlayerPieceCoords,
                checkMove,
                board,
                pathChecker
            )
        ) {
            return false;
        }

        System.out.println("Returning true because king can't escape!");
        return true;
    }

    private boolean kingInCheck(
        ArrayList<Coordinates> playerCoords,
        Coordinates kingCoords,
        ClearPathChecker pathChecker,
        IChessBoard board
    ) {
        for (Coordinates coordinates : playerCoords) {
            IPiece piece = board.getPieceArray()[coordinates.getColumnCoordinate()][coordinates.getRowCoordinate()];

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

    private boolean threateningPieceCanBeTaken(
        Coordinates kingCoordinates,
        ArrayList<Coordinates> alliedPieceCoordinates,
        ChessMove checkMove,
        IChessBoard board,
        ClearPathChecker pathChecker
    ) {
        for (Coordinates pieceCoordinates : alliedPieceCoordinates) {
            IPiece alliedPiece = board.getPieceArray()[pieceCoordinates.getColumnCoordinate()][pieceCoordinates.getRowCoordinate()];
            ChessMove potentialTakeMove = new ChessMove(
                pieceCoordinates.getColumnCoordinate(),
                pieceCoordinates.getRowCoordinate(),
                checkMove.getMoveFromColumn(),
                checkMove.getMoveFromRow()
            );

            if (
                moveValid(
                    alliedPiece,
                    potentialTakeMove,
                    board,
                    pathChecker,
                    false
                )
            ) {
                System.out.println(
                    "Threatening piece can be taken by a allied piece"
                );
                System.out.println("Allied piece is " + alliedPiece);
                return true;
            }
        }

        IPiece threatenedKing = board.getPieceArray()[kingCoordinates.getColumnCoordinate()][kingCoordinates.getRowCoordinate()];
        ChessMove potentialKingTakeMove = new ChessMove(
            kingCoordinates.getColumnCoordinate(),
            kingCoordinates.getRowCoordinate(),
            checkMove.getMoveToColumn(),
            checkMove.getMoveToRow()
        );

        if (
            moveValid(
                threatenedKing,
                potentialKingTakeMove,
                board,
                pathChecker,
                false
            )
        ) {
            System.out.println("Threatening piece can be taken by king");
            return true;
        }
        return false;
    }

    private boolean moveCanBeBlockedByAlliedPiece(
        ArrayList<Coordinates> alliedPieceCoordinates,
        ChessMove checkMove,
        IChessBoard board,
        ClearPathChecker pathChecker
    ) {
        ArrayList<Coordinates> coordinatesBetweenKingAndCheckerPiece = pathChecker.getCoordinatesBetweenMove(
            checkMove,
            false
        );
        for (Coordinates targetCoordinates : coordinatesBetweenKingAndCheckerPiece) {
            for (Coordinates pieceCoordinates : alliedPieceCoordinates) {
                IPiece alliedPiece = board.getPieceArray()[pieceCoordinates.getColumnCoordinate()][pieceCoordinates.getRowCoordinate()];
                ChessMove potentialBlockingMove = new ChessMove(
                    pieceCoordinates.getColumnCoordinate(),
                    pieceCoordinates.getRowCoordinate(),
                    targetCoordinates.getColumnCoordinate(),
                    targetCoordinates.getRowCoordinate()
                );

                if (
                    alliedPiece.getType() == Piece.Types.KING ||
                    alliedPiece.getType() == Piece.Types.KNIGHT
                ) {
                    continue;
                }

                if (
                    moveValid(
                        alliedPiece,
                        potentialBlockingMove,
                        board,
                        pathChecker,
                        false
                    )
                ) {
                    System.out.println("Move can blocked by a allied piece");
                    System.out.println("Allied piece is " + alliedPiece);
                    return true;
                }
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
