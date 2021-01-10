package chess.board;

public class BoardManager {
    public ChessBoard buildBoard(){
        ChessBoardGUI boardGUI = new ChessBoardGUI(new GUISetter());
        return new ChessBoard(boardGUI);
    }
}
