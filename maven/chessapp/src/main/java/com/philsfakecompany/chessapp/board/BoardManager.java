package com.philsfakecompany.chessapp.board.BoardManager;

import com.philsfakecompany.chessapp.board.ChessBoard.*;
import com.philsfakecompany.chessapp.board.GUI.ChessBoardGUI.*;
import com.philsfakecompany.chessapp.board.GUI.GUISetter.*;
import com.philsfakecompany.chessapp.board.GUI.GUIUpdater.*;

public class BoardManager {

    public ChessBoard buildBoard() {
        ChessBoardGUI boardGUI = new ChessBoardGUI(
            new GUISetter(),
            new GUIUpdater()
        );
        return new ChessBoard(boardGUI);
    }
}
