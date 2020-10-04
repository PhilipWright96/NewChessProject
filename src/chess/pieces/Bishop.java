package chess.pieces;

import chess.board.IChessBoard;
import chess.game.ChessMove;

public class Bishop extends Piece {

    public boolean moveValid(ChessMove move, IChessBoard chessBoard){
        if (move.getRowChangeNum() == move.getColumnChangeNum()){
            return true;
        }
        System.out.println("Error: Incorrect move logic for bishop");
        return false;
    }
}