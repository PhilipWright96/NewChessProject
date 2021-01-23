package chess.board;
import chess.board.GUI.*;

public class BoardManager {
    public ChessBoard buildBoard(){
        ChessBoardGUI boardGUI = new ChessBoardGUI(new GUISetter(), new GUIUpdater());
        return new ChessBoard(boardGUI);
    }
}
