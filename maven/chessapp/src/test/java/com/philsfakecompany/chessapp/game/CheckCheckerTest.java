package com.philsfakecompany.chessapp.game;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.*;
import game.*;
import input.*;
import java.util.HashMap;
import org.junit.Test;
import pieces.*;
import player.*;
import util.*;

public class CheckCheckerTest {

    @Test
    public void ownKingInCheck_withKingInCheck_verifyCorrectChecksCalled() {
        // Given
        ChessMove mockMove = mock(ChessMove.class);

        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        IChessBoard mockBoard = mock(IChessBoard.class);

        ClearPathChecker mockPathChecker = mock(ClearPathChecker.class);
        when(mockPathChecker.pathForMoveClear(any(), any(), eq(false)))
            .thenReturn(true);

        IPiece silverKing = PieceFactory.constructPiece(
            Piece.Types.KING,
            Teams.SILVER
        );
        ChessBoard.Coordinates mockKingCoordinates = new ChessBoard(null)
            .new Coordinates(1, 1);

        IPiece goldPawn = mock(IPiece.class);
        ChessBoard.Coordinates mockPawnCoordinates = new ChessBoard(null)
            .new Coordinates(2, 2);
        when(goldPawn.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(goldPawn.getType()).thenReturn(Piece.Types.PAWN);

        HashMap<IPiece, ChessBoard.Coordinates> silverPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(silverKing, mockKingCoordinates);
            }
        };
        HashMap<IPiece, ChessBoard.Coordinates> goldPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(goldPawn, mockPawnCoordinates);
            }
        };

        ChessBoard.PieceToCoordinates mockPieceToCoordinateMap = mock(
            ChessBoard.PieceToCoordinates.class
        );
        mockPieceToCoordinateMap.silverPieceToCoordinates =
            silverPieceToCoordinates;
        mockPieceToCoordinateMap.goldPieceToCoordinates =
            goldPieceToCoordinates;

        when(mockBoard.getPieceToCoordinatesMap())
            .thenReturn(mockPieceToCoordinateMap);

        // When
        boolean result = new CheckChecker()
            .ownKingInCheck(mockMove, mockPlayer, mockBoard, mockPathChecker);

        // Then
        verify(mockBoard).movePiece(mockMove, false);
        verify(mockPlayer).getTeam();
        verify(goldPawn).moveValid(any(ChessMove.class), eq(mockBoard));
        verify(mockBoard).undoMovePiece(mockMove);
        assertEquals(true, result);
    }

    @Test
    public void ownKingInCheck_withKingNotInCheck_verifyCorrectChecksCalled() {
        // Given
        ChessMove mockMove = mock(ChessMove.class);

        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        IChessBoard mockBoard = mock(IChessBoard.class);

        ClearPathChecker mockPathChecker = mock(ClearPathChecker.class);
        when(mockPathChecker.pathForMoveClear(any(), any(), eq(false)))
            .thenReturn(true);

        IPiece silverKing = PieceFactory.constructPiece(
            Piece.Types.KING,
            Teams.SILVER
        );
        ChessBoard.Coordinates mockKingCoordinates = new ChessBoard(null)
            .new Coordinates(1, 1);

        IPiece goldPawn = mock(IPiece.class);
        ChessBoard.Coordinates mockPawnCoordinates = new ChessBoard(null)
            .new Coordinates(2, 2);
        when(goldPawn.moveValid(any(), eq(mockBoard))).thenReturn(false);
        when(goldPawn.getType()).thenReturn(Piece.Types.PAWN);

        HashMap<IPiece, ChessBoard.Coordinates> silverPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(silverKing, mockKingCoordinates);
            }
        };
        HashMap<IPiece, ChessBoard.Coordinates> goldPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(goldPawn, mockPawnCoordinates);
            }
        };

        ChessBoard.PieceToCoordinates mockPieceToCoordinateMap = mock(
            ChessBoard.PieceToCoordinates.class
        );
        mockPieceToCoordinateMap.silverPieceToCoordinates =
            silverPieceToCoordinates;
        mockPieceToCoordinateMap.goldPieceToCoordinates =
            goldPieceToCoordinates;

        when(mockBoard.getPieceToCoordinatesMap())
            .thenReturn(mockPieceToCoordinateMap);

        // When
        boolean result = new CheckChecker()
            .ownKingInCheck(mockMove, mockPlayer, mockBoard, mockPathChecker);

        // Then
        verify(mockBoard).movePiece(mockMove, false);
        verify(mockPlayer).getTeam();
        verify(goldPawn).moveValid(any(ChessMove.class), eq(mockBoard));
        verify(mockBoard).undoMovePiece(mockMove);
        assertEquals(false, result);
    }

    @Test
    public void opposingKingInCheck_withOpposingKingInCheck_verifyCorrectChecksCalled() {
        // Given
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        IChessBoard mockBoard = mock(IChessBoard.class);

        ClearPathChecker mockPathChecker = mock(ClearPathChecker.class);
        when(mockPathChecker.pathForMoveClear(any(), any(), eq(false)))
            .thenReturn(true);

        IPiece goldKing = PieceFactory.constructPiece(
            Piece.Types.KING,
            Teams.GOLD
        );
        ChessBoard.Coordinates mockKingCoordinates = new ChessBoard(null)
            .new Coordinates(1, 1);

        IPiece silverPawn = mock(IPiece.class);
        ChessBoard.Coordinates mockPawnCoordinates = new ChessBoard(null)
            .new Coordinates(2, 2);
        when(silverPawn.moveValid(any(), eq(mockBoard))).thenReturn(true);
        when(silverPawn.getType()).thenReturn(Piece.Types.PAWN);

        HashMap<IPiece, ChessBoard.Coordinates> silverPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(silverPawn, mockPawnCoordinates);
            }
        };
        HashMap<IPiece, ChessBoard.Coordinates> goldPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(goldKing, mockKingCoordinates);
            }
        };

        ChessBoard.PieceToCoordinates mockPieceToCoordinateMap = mock(
            ChessBoard.PieceToCoordinates.class
        );
        mockPieceToCoordinateMap.silverPieceToCoordinates =
            silverPieceToCoordinates;
        mockPieceToCoordinateMap.goldPieceToCoordinates =
            goldPieceToCoordinates;

        when(mockBoard.getPieceToCoordinatesMap())
            .thenReturn(mockPieceToCoordinateMap);

        // When
        boolean result = new CheckChecker()
            .opposingKingInCheck(mockPlayer, mockBoard, mockPathChecker);

        // Then
        verify(mockPlayer).getTeam();
        verify(silverPawn).moveValid(any(ChessMove.class), eq(mockBoard));
        assertEquals(true, result);
    }

    @Test
    public void opposingKingInCheck_withOpposingKingNotInCheck_verifyCorrectChecksCalled() {
        // Given
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getTeam()).thenReturn(Teams.SILVER);

        IChessBoard mockBoard = mock(IChessBoard.class);

        ClearPathChecker mockPathChecker = mock(ClearPathChecker.class);
        when(mockPathChecker.pathForMoveClear(any(), any(), eq(false)))
            .thenReturn(true);

        IPiece goldKing = PieceFactory.constructPiece(
            Piece.Types.KING,
            Teams.GOLD
        );
        ChessBoard.Coordinates mockKingCoordinates = new ChessBoard(null)
            .new Coordinates(1, 1);

        IPiece silverPawn = mock(IPiece.class);
        ChessBoard.Coordinates mockPawnCoordinates = new ChessBoard(null)
            .new Coordinates(2, 2);
        when(silverPawn.moveValid(any(), eq(mockBoard))).thenReturn(false);
        when(silverPawn.getType()).thenReturn(Piece.Types.PAWN);

        HashMap<IPiece, ChessBoard.Coordinates> silverPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(silverPawn, mockPawnCoordinates);
            }
        };
        HashMap<IPiece, ChessBoard.Coordinates> goldPieceToCoordinates = new HashMap<IPiece, ChessBoard.Coordinates>() {
            {
                put(goldKing, mockKingCoordinates);
            }
        };

        ChessBoard.PieceToCoordinates mockPieceToCoordinateMap = mock(
            ChessBoard.PieceToCoordinates.class
        );
        mockPieceToCoordinateMap.silverPieceToCoordinates =
            silverPieceToCoordinates;
        mockPieceToCoordinateMap.goldPieceToCoordinates =
            goldPieceToCoordinates;

        when(mockBoard.getPieceToCoordinatesMap())
            .thenReturn(mockPieceToCoordinateMap);

        // When
        boolean result = new CheckChecker()
            .opposingKingInCheck(mockPlayer, mockBoard, mockPathChecker);

        // Then
        verify(mockPlayer).getTeam();
        verify(silverPawn).moveValid(any(ChessMove.class), eq(mockBoard));
        assertEquals(false, result);
    }
}
