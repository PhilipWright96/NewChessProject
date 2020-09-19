package chess.pieces;

import chess.game.ChessMove;
import chess.util.Teams;

public class Pawn extends Piece{

    public boolean moveValid(ChessMove move){
        if (checkCorrectColumn(move) && checkCorrectRow(move)){
            return true;
        }
        return false;
    }

    private boolean checkCorrectColumn(ChessMove move){
        if (move.getMoveFromColumn() == move.getMoveToColumn()){
            return true;
        }
        return false;
    }

    private boolean checkCorrectRow(ChessMove move){
        if (getTeam() == Teams.SILVER){
            if (move.getMoveFromRow() - 1 == move.getMoveToRow()){
                return true;
            }
            return false;
        }
        else {
            if (move.getMoveFromRow() + 1 == move.getMoveToRow()){
                return true;
            }
            return false;
        }
    }
}