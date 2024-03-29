package com.philsfakecompany.chessapp.board.GUI;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import board.GUI.*;
import game.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.junit.Test;
import pieces.*;
import util.*;

public class GUIUpdaterTest {

    @Test
    public void updateBoardWithPawns_setsPawnsOnGivenBoardArray() {
        // Given
        JButton[][] mockBoard = new JButton[8][8];
        GUISetter mockSetter = new GUISetter();
        mockSetter.arrangeButtons(mockBoard);

        GUIUpdater guiUpdater = new GUIUpdater();

        // When
        guiUpdater.updateBoardWithPawns(mockBoard);

        // Then
        for (int k = 0; k < mockBoard.length; k++) {
            JButton goldSquare = mockBoard[k][1];
            assertEquals(true, goldSquare.getIcon() != null);
            JButton silverSquare = mockBoard[k][mockBoard.length - 2];
            assertEquals(true, silverSquare.getIcon() != null);
        }
    }

    @Test
    public void updateBoardWithSpecialPieces_setsSpecialPiecesOnGivenBoardArray() {
        // Given
        JButton[][] mockBoard = new JButton[8][8];
        GUISetter mockSetter = new GUISetter();
        mockSetter.arrangeButtons(mockBoard);

        GUIUpdater guiUpdater = new GUIUpdater();

        // When
        guiUpdater.updateBoardWithSpecialPieces(mockBoard);

        // Then
        for (int k = 0; k < mockBoard.length; k++) {
            JButton goldSquare = mockBoard[k][0];
            assertEquals(true, goldSquare.getIcon() != null);
            JButton silverSquare = mockBoard[k][mockBoard.length - 1];
            assertEquals(true, silverSquare.getIcon() != null);
        }
    }

    @Test
    public void updateBoardWithNewMove_updatesBoardWithMove() {
        // Given
        GUISetter mockSetter = new GUISetter();
        JButton[][] mockBoard = new JButton[8][8];
        mockSetter.arrangeButtons(mockBoard);
        mockBoard[0][0].setIcon(new ImageIcon(ChessSprites.GOLD_PAWN));

        ChessMove mockMove = mock(ChessMove.class);
        when(mockMove.getMoveFromColumn()).thenReturn(0);
        when(mockMove.getMoveFromRow()).thenReturn(0);
        when(mockMove.getMoveToColumn()).thenReturn(1);
        when(mockMove.getMoveToRow()).thenReturn(1);

        IPiece mockPiece = PieceFactory.constructPiece(
            Piece.Types.PAWN,
            Teams.GOLD
        );

        GUIUpdater guiUpdater = new GUIUpdater();

        // When
        guiUpdater.updateBoardWithNewMove(mockBoard, mockMove, mockPiece);

        // Then
        verify(mockMove).getMoveFromColumn();
        verify(mockMove).getMoveFromRow();
        verify(mockMove).getMoveToColumn();
        verify(mockMove).getMoveToRow();
        assertEquals(true, mockBoard[0][0].getIcon() == null);
        assertEquals(true, mockBoard[1][1].getIcon() != null);
    }
}
