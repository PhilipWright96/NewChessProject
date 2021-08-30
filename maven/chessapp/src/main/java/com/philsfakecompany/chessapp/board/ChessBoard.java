package board;

import board.GUI.*;
import game.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pieces.*;
import util.*;

public class ChessBoard implements IChessBoard {

    private IChessBoardGUI chessBoardGUI;
    private IPiece[][] pieceArray;
    private PieceToCoordinates allPiecesToCoordinates;

    public static ArrayList<Piece.Types> pieceOrder = new ArrayList<Piece.Types>() {
        {
            add(Piece.Types.ROOK);
            add(Piece.Types.KNIGHT);
            add(Piece.Types.BISHOP);
            add(Piece.Types.QUEEN);
            add(Piece.Types.KING);
            add(Piece.Types.BISHOP);
            add(Piece.Types.KNIGHT);
            add(Piece.Types.ROOK);
        }
    };

    public ChessBoard(IChessBoardGUI boardGUI) {
        chessBoardGUI = boardGUI;
        pieceArray = new Piece[8][8];
        allPiecesToCoordinates = new PieceToCoordinates();
    }

    public void initializeChessBoard() {
        chessBoardGUI.initializeBoardGUI();
        addPawns();
        addSpecialPieces();
    }

    public IPiece[][] getPieceArray() {
        return pieceArray;
    }

    public void movePiece(ChessMove inputMove, boolean updateGUI) {
        IPiece pieceBeingMoved = getPieceBeingMoved(inputMove);
        pieceArray[inputMove.getMoveFromColumn()][inputMove.getMoveFromRow()] =
            null;
        pieceArray[inputMove.getMoveToColumn()][inputMove.getMoveToRow()] =
            pieceBeingMoved;

        allPiecesToCoordinates.updatePieceWithNewCoordinates(
            pieceBeingMoved,
            inputMove.getMoveFromRow(),
            inputMove.getMoveFromColumn(),
            inputMove.getMoveToRow(),
            inputMove.getMoveToColumn()
        );

        if (updateGUI) {
            chessBoardGUI.updateBoardWithNewMove(inputMove, pieceBeingMoved);
        }
    }

    public void undoMovePiece(ChessMove move) {
        IPiece pieceAlreadyMoved =
            pieceArray[move.getMoveToColumn()][move.getMoveToRow()];
        pieceArray[move.getMoveToColumn()][move.getMoveToRow()] = null;
        pieceArray[move.getMoveFromColumn()][move.getMoveFromRow()] =
            pieceAlreadyMoved;

        allPiecesToCoordinates.updatePieceWithNewCoordinates(
            pieceAlreadyMoved,
            move.getMoveToRow(),
            move.getMoveToColumn(),
            move.getMoveFromRow(),
            move.getMoveFromColumn()
        );
    }

    public IPiece getPieceBeingMoved(ChessMove move) {
        return pieceArray[move.getMoveFromColumn()][move.getMoveFromRow()];
    }

    public IPiece getPieceBeingTaken(ChessMove move) {
        return pieceArray[move.getMoveToColumn()][move.getMoveToRow()];
    }

    public PieceToCoordinates getPieceToCoordinatesMap() {
        return allPiecesToCoordinates;
    }

    private void addPawns() {
        for (int k = 0; k < pieceArray.length; k++) {
            Piece goldPawn = PieceFactory.constructPiece(
                Piece.Types.PAWN,
                Teams.GOLD
            );
            pieceArray[k][1] = goldPawn;
            allPiecesToCoordinates.storePieceWithCoordinates(goldPawn, 1, k);

            Piece silverPawn = PieceFactory.constructPiece(
                Piece.Types.PAWN,
                Teams.SILVER
            );
            pieceArray[k][pieceArray.length - 2] = silverPawn;
            allPiecesToCoordinates.storePieceWithCoordinates(
                silverPawn,
                pieceArray.length - 2,
                k
            );
        }

        chessBoardGUI.updateBoardWithPawns();
    }

    private void addSpecialPieces() {
        for (int l = 0; l < pieceArray.length; l++) {
            Piece goldPiece = PieceFactory.constructPiece(
                pieceOrder.get(l),
                Teams.GOLD
            );
            pieceArray[l][0] = goldPiece;
            allPiecesToCoordinates.storePieceWithCoordinates(goldPiece, 0, l);

            Piece silverPiece = PieceFactory.constructPiece(
                pieceOrder.get(l),
                Teams.SILVER
            );
            pieceArray[l][pieceArray.length - 1] = silverPiece;
            allPiecesToCoordinates.storePieceWithCoordinates(
                silverPiece,
                pieceArray.length - 1,
                l
            );
        }

        chessBoardGUI.updateBoardWithSpecialPieces();
    }

    public class PieceToCoordinates {

        public HashMap<IPiece, Coordinates> silverPieceToCoordinates;
        public HashMap<IPiece, Coordinates> goldPieceToCoordinates;

        public ArrayList<Coordinates> silverPieceCoordinates;
        public ArrayList<Coordinates> goldPieceCoordinates;

        private PieceToCoordinates() {
            this.silverPieceToCoordinates = new HashMap<IPiece, Coordinates>();
            this.goldPieceToCoordinates = new HashMap<IPiece, Coordinates>();

            this.silverPieceCoordinates = new ArrayList<Coordinates>();
            this.goldPieceCoordinates = new ArrayList<Coordinates>();
        }

        private void storePieceWithCoordinates(
            IPiece piece,
            int rowCoordinate,
            int columnCoordinate
        ) {
            Coordinates coordinates = new Coordinates(
                rowCoordinate,
                columnCoordinate
            );
            if (piece.getTeam() == Teams.SILVER) {
                silverPieceToCoordinates.put(piece, coordinates);
                silverPieceCoordinates.add(coordinates);
            } else {
                goldPieceToCoordinates.put(piece, coordinates);
                goldPieceCoordinates.add(coordinates);
            }
        }

        public void updatePieceWithNewCoordinates(
            IPiece piece,
            int oldRowCoordinate,
            int oldColumnCoordinate,
            int newRowCoordinate,
            int newColumnCoordinate
        ) {
            Coordinates newCoordinates = new Coordinates(
                newRowCoordinate,
                newColumnCoordinate
            );

            if (piece.getTeam() == Teams.SILVER) {
                List<Coordinates> oldSilverCoordsToRemove = new ArrayList<Coordinates>();
                for (Coordinates coordinate : silverPieceCoordinates) {
                    if (
                        (
                            coordinate.getRowCoordinate() == oldRowCoordinate &&
                            coordinate.getColumnCoordinate() ==
                            oldColumnCoordinate
                        ) ||
                        (
                            coordinate.getRowCoordinate() == newRowCoordinate &&
                            coordinate.getColumnCoordinate() ==
                            newColumnCoordinate
                        )
                    ) {
                        oldSilverCoordsToRemove.add(coordinate);
                    }
                }
                silverPieceCoordinates.removeAll(oldSilverCoordsToRemove);
                silverPieceToCoordinates.put(piece, newCoordinates);
                silverPieceCoordinates.add(newCoordinates);
            } else {
                List<Coordinates> oldGoldCoordsToRemove = new ArrayList<Coordinates>();
                for (Coordinates coordinate : goldPieceCoordinates) {
                    if (
                        (
                            coordinate.getRowCoordinate() == oldRowCoordinate &&
                            coordinate.getColumnCoordinate() ==
                            oldColumnCoordinate
                        ) ||
                        (
                            coordinate.getRowCoordinate() == newRowCoordinate &&
                            coordinate.getColumnCoordinate() ==
                            newColumnCoordinate
                        )
                    ) {
                        oldGoldCoordsToRemove.add(coordinate);
                    }
                }
                goldPieceCoordinates.removeAll(oldGoldCoordsToRemove);
                goldPieceToCoordinates.put(piece, newCoordinates);
                goldPieceCoordinates.add(newCoordinates);
            }
        }
    }
}
