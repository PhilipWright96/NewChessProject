package chess.board;

public class BoardManager {
    public ChessBoard buildBoard(){
        return new ChessBoard(new ChessBoardGUI());
    }
}
